package com.signicat.interview.service.impl;

import com.signicat.interview.config.security.JwtTokenProvider;
import com.signicat.interview.domain.*;
import com.signicat.interview.exception.*;
import com.signicat.interview.repository.MemberRepository;
import com.signicat.interview.service.AuthorityService;
import com.signicat.interview.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class MemberServiceImpl implements MemberService, UserDetailsService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthorityService authorityService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public MemberServiceImpl(MemberRepository memberRepository,
                             JwtTokenProvider jwtTokenProvider,
                             AuthorityService authorityService,
                             @Lazy PasswordEncoder passwordEncoder,
                             @Lazy AuthenticationManager authenticationManager) {
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authorityService = authorityService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Member findByUserName(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Member findById(String id) {
        return memberRepository.findById(Long.parseLong(id))
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Member findById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
    }

    @Override
    public Page<Member> findUsers(String name, String nationalCode, int pageSize, int pageIndex) {
        return memberRepository.findAllByNationalCodeAndFirstNameAndLastName(
                nationalCode,
                StringUtils.trim(name),
                StringUtils.trim(name),
                StringUtils.trim(name),
                PageRequest.of(pageIndex, pageSize));
    }

    @Override
    public Member createUser(String username, String password, String firstName, String lastName,
                             String nationalCode, String mobileNumber, Boolean isEnabled,
                             Set<String> authorityIds) {
        Set<Authority> authorities = authorityIds.stream().map(authorityService::findById)
                .collect(Collectors.toSet());

        return memberRepository.save(Member.builder()
                .firstName(firstName)
                .lastName(lastName)
                .nationalCode(nationalCode)
                .mobileNumber(mobileNumber)
                .username(username)
                .password(passwordEncoder.encode(password))
                .isEnabled(isEnabled)
                .authorities(authorities)
                .isWorking(false)
                .build());
    }

    @Transactional
    @Override
    public Member updateUser(String userId, String username, String password, String firstName,
                             String lastName, String nationalCode, String mobileNumber, Boolean isEnabled,
                             Set<String> authorityIds) {

        Member member = this.findById(userId);

        if (Objects.nonNull(username))
            member.setUsername(username);

        if (Objects.nonNull(password))
            member.setPassword(passwordEncoder.encode(password));

        if (Objects.nonNull(firstName))
            member.setFirstName(firstName);

        if (Objects.nonNull(lastName))
            member.setLastName(lastName);

        if (Objects.nonNull(nationalCode))
            member.setNationalCode(nationalCode);

        if (Objects.nonNull(mobileNumber))
            member.setMobileNumber(mobileNumber);

        if (Objects.nonNull(isEnabled))
            member.setIsEnabled(isEnabled);

        if (!authorityIds.isEmpty())
            member.setAuthorities(authorityIds.stream().map(authorityService::findById).collect(Collectors.toSet()));

        if (authorityIds.isEmpty())
            member.setAuthorities(null);

        return member;
    }

    @Override
    public AuthenticationResult login(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            Member member = findByUserName(username);
            return jwtTokenProvider.generateToken(member);
        } catch (DisabledException e) {
            throw new MemberAccountDisabledException();
        } catch (BadCredentialsException e) {
            throw new BadCredentialException();
        }
    }

    @Override
    public AuthenticationResult refreshAccessToken(String refreshToken) {
        return jwtTokenProvider.refreshToken(refreshToken);
    }

    @Override
    public Member loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.findByUserName(username);
    }

    @Override
    public List<Member> getWorkingMembersList() {
        return memberRepository.findAllByIsWorking(true);
    }

    @Override
    public void changePassword(Long id, ProtectedValue oldPassword, ProtectedValue newPassword, ProtectedValue confirmPassword) {
        Member member = findById(id);

        if (!passwordEncoder.matches(oldPassword.getValue(), member.getPassword())) {
            throw new InvalidOldPasswordException();
        }

        if (!StringUtils.equals(newPassword.getValue(), confirmPassword.getValue())) {
            throw new NewPasswordAndConfirmPasswordNotSameException();
        }

        member.setPassword(passwordEncoder.encode(newPassword.getValue()));
    }

}

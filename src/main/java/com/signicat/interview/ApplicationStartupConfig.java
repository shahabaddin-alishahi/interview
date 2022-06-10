package com.signicat.interview;

import com.signicat.interview.domain.Authority;
import com.signicat.interview.domain.Member;
import com.signicat.interview.repository.AuthorityRepository;
import com.signicat.interview.repository.MemberRepository;
import com.signicat.interview.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class ApplicationStartupConfig {

    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;


    @Bean
    public void populateDatabaseWithAdminUser() {
        Set<Authority> authoritySet = new HashSet<>(authorityRepository.findAll());

        memberRepository.findByUsername("admin").ifPresentOrElse(
                admin -> {
                    if (admin.getAuthorities().size() != authoritySet.size()) {
                        admin.setAuthorities(authoritySet);
                        memberRepository.save(admin);
                    }
                },
                () -> {
                    String password = RandomStringUtils.randomAlphanumeric(20);

                    memberRepository.save(Member.builder()
                            .id(1L)
                            .username("admin")
                            .password(passwordEncoder.encode(password))
                            .mobileNumber("1")
                            .nationalCode("1")
                            .firstName("admin firstName")
                            .lastName("admin lastName")
                            .isEnabled(true)
                            .isWorking(true)
                            .authorities(authoritySet)
                            .build());

                    log.info("Admin account's password is set to : {}", password);
                }
        );
    }
}

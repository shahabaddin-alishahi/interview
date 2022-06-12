package com.signicat.interview.controller.member.create;

import com.signicat.interview.domain.Authority;
import com.signicat.interview.domain.Member;
import com.signicat.interview.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collectors;

@PreAuthorize("hasAuthority('MEMBER_CREATE')")
@RequiredArgsConstructor
@RestController
public class CreateUserController {

    private final MemberService memberService;

    @PostMapping("${apis.secure}/member")
    public CreateUserResponse handle(@RequestBody @Valid CreateUserRequest request){

        Member member = memberService.createUser(
                request.getUsername(),request.getPassword(), request.getFirstName(),
                request.getLastName(), request.getNationalCode(),request.getMobileNumber(),
                request.getIsEnabled(),request.getUserGroupId() , request.getAuthorityIds());

        return CreateUserResponse.builder()
                .id(String.valueOf(member.getId()))
                .createDate(String.valueOf(member.getCreateDate()))
                .updateDate(String.valueOf(member.getUpdateDate()))
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .username(member.getUsername())
                .mobileNumber(member.getMobileNumber())
                .nationalCode(member.getNationalCode())
                .isEnabled(member.isEnabled())
                .isWorking(member.getIsWorking())
                .userGroupId(String.valueOf(member.getUserGroup().getId()))
                .userGroupTitle(member.getUserGroup().getTitle())
                .authorityTitles(member.getAuthorities().stream().map(Authority::getDescription).collect(Collectors.toSet()))
                .build();
    }
}

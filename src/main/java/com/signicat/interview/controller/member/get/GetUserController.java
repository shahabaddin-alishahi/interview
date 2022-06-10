package com.signicat.interview.controller.member.get;

import com.signicat.interview.domain.Authority;
import com.signicat.interview.domain.Member;
import com.signicat.interview.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@PreAuthorize("hasAuthority('MEMBER_GET')")
@RequiredArgsConstructor
@RestController
public class GetUserController {

    private final MemberService memberService;

    @GetMapping("${apis.secure}/member/{userId}")
    public GetUserResponse handle(@PathVariable String userId){
        Member member = memberService.findById(userId);
        return GetUserResponse.builder()
                .id(String.valueOf(member.getId()))
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .username(member.getUsername())
                .mobileNumber(member.getMobileNumber())
                .nationalCode(member.getNationalCode())
                .isEnabled(member.isEnabled())
                .authorityTitles(member.getAuthorities().stream().map(Authority::getDescription)
                        .collect(Collectors.toSet()))
                .authorityIds(member.getAuthorities().stream().map(authority -> String.valueOf(authority.getId()))
                        .collect(Collectors.toSet()))
                .build();
    }
}

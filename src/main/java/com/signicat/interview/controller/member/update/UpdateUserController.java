package com.signicat.interview.controller.member.update;

import com.signicat.interview.domain.Authority;
import com.signicat.interview.domain.Member;
import com.signicat.interview.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;

@PreAuthorize("hasAuthority('MEMBER_UPDATE')")
@RequiredArgsConstructor
@RestController
public class UpdateUserController {

    private final MemberService memberService;

    @PutMapping("${apis.secure}/member/{userId}")
    public UpdateUserResponse handle(@PathVariable String userId,
                                     @RequestBody @Valid UpdateUserRequest request) {

        Member member = memberService.updateUser(userId, request.getUsername(),
                request.getPassword(), request.getFirstName(), request.getLastName(),
                request.getNationalCode(), request.getMobileNumber(), request.getIsEnabled(), request.getIsWorking(),
                request.getUserGroupId(),
                Objects.isNull(request.getAuthorityIds()) ? Collections.emptySet() : request.getAuthorityIds());

        return UpdateUserResponse.builder()
                .id(String.valueOf(member.getId()))
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .username(member.getUsername())
                .mobileNumber(member.getMobileNumber())
                .nationalCode(member.getNationalCode())
                .isEnabled(member.isEnabled())
                .isWorking(member.getIsWorking())
                .createDate(String.valueOf(member.getCreateDate()))
                .updateDate(String.valueOf(member.getUpdateDate()))
                .userGroupId(String.valueOf(member.getUserGroup().getId()))
                .userGroupTitle(member.getUserGroup().getTitle())
                .authorityTitles(member.getAuthorities().stream().map(Authority::getDescription).collect(Collectors.toSet()))
                .build();
    }
}
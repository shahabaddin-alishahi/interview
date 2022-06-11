package com.signicat.interview.controller.member.password;

import com.signicat.interview.config.security.OnlineUser;
import com.signicat.interview.domain.GeneralStatus;
import com.signicat.interview.domain.ProtectedValue;
import com.signicat.interview.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@PreAuthorize("hasAuthority('MEMBER_CHANGE_PASSWORD')")
@RestController
@RequiredArgsConstructor
public class ChangeUserPasswordController {

    private final MemberService memberService;
    private final OnlineUser onlineUser;

    @PutMapping("${apis.secure}/member/change-password")
    public GeneralStatus handle(@Valid @RequestBody ChangePasswordRequest request) {
        memberService.changePassword(onlineUser.getId(),
                ProtectedValue.builder().value(request.getOldPassword()).build(),
                ProtectedValue.builder().value(request.getNewPassword()).build(),
                ProtectedValue.builder().value(request.getConfirmPassword()).build());

        return GeneralStatus.builder()
                .status(GeneralStatus.Status.SUCCESS)
                .build();
    }
}

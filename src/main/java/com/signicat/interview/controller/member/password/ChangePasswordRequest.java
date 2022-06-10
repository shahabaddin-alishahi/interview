package com.signicat.interview.controller.member.password;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class ChangePasswordRequest {

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;

    @NotBlank
    private String confirmPassword;
}

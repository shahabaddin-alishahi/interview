package com.signicat.interview.controller.member.create;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Builder
@Data
public class CreateUserRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String mobileNumber;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    private String nationalCode;
    private Boolean isEnabled;
    private Set<String> authorityIds;
    @NotNull
    private String userGroupId;

}

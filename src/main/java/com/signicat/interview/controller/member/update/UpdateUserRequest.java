package com.signicat.interview.controller.member.update;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class UpdateUserRequest {
    private String username;
    private String password;
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private Boolean isWorking;
    private Boolean isEnabled;
    private Set<String> authorityIds;
    private String userGroupId;

}

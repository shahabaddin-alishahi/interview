package com.signicat.interview.controller.member.create;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class CreateUserResponse {
    private String id;
    private String username;
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private Boolean isEnabled;
    private Set<String> authorityTitles;
}

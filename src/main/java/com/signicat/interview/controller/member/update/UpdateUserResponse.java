package com.signicat.interview.controller.member.update;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class UpdateUserResponse {
    private String id;
    private String username;
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private Boolean isEnabled;
    private Boolean isWorking;
    private Set<String> authorityTitles;

}
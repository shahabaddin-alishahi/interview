package com.signicat.interview.controller.member.get;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class GetUserResponse {

    private String id;
    private String username;
    private String password;
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private Boolean isEnabled;
    private Set<String> authorityTitles;
    private Set<String> authorityIds;
    private String createDate;
    private String updateDate;
}

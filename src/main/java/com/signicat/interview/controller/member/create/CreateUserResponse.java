package com.signicat.interview.controller.member.create;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Setter
@Getter
public class CreateUserResponse {
    private String id;
    private String username;
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private Boolean isEnabled;
    private Set<String> authorityTitles;
    private String createDate;
    private String updateDate;
    private Boolean isWorking;
    private String userGroupId;
    private String userGroupTitle;


}

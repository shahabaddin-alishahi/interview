package com.signicat.interview.controller.member.update;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Setter
@Getter
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
    private String createDate;
    private String updateDate;
    private String userGroupTitle;
    private String userGroupId;

}

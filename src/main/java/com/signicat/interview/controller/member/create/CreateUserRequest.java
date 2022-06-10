package com.signicat.interview.controller.member.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Builder
@Data
public class CreateUserRequest {
    private String username;
    private String password;
    private String mobileNumber;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private Boolean isEnabled;
    private Set<String> authorityIds;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FlowStatusType {
        private String statusType;
    }

}

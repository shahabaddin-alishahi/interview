package com.signicat.interview.controller.member.list;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UsersListResponse {

    @Data
    @Builder
    public static class UserResponse {
        private String id;
        private String username;
        private String mobileNumber;
        private String firstName;
        private String lastName;
        private String nationalCode;
        private Boolean isWorking;
        private Boolean isEnabled;
        private String createDate;
        private String updateDate;
    }

    private List<UserResponse> items;
    private Long totalElements;
    private Long totalPages;
}

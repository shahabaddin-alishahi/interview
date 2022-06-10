package com.signicat.interview.controller.member.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
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
    private Boolean isEnabled;
    private Set<String> authorityIds;
    private Integer maxTaskCount;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class FlowStatusType {
        private String statusType;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TaskGroup {
        private String flowType;
        private List<FlowStatusType> statusTypes;
    }

    private List<TaskGroup> taskGroupList;
}

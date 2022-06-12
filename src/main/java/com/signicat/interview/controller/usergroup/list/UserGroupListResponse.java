package com.signicat.interview.controller.usergroup.list;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserGroupListResponse {

    @Data
    @Builder
    public static class UserGroupItem {
        private String id;
        private String title;
        private String createDate;
        private String updateDate;
    }

    private List<UserGroupItem> items;
}

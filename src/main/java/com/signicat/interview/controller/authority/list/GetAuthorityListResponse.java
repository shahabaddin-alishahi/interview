package com.signicat.interview.controller.authority.list;

import com.signicat.interview.controller.member.list.UsersListResponse;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class GetAuthorityListResponse {

    @Data
    @Builder
    public static class AuthorityItem {
        private String id;
        private String title;
        private String description;
        private String createDate;
        private String updateDate;
    }

    private List<AuthorityItem> items;
}

package com.signicat.interview.controller.usergroup.get;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class GetUserGroupResponse {

    private String id;
    private String title;
    private String createDate;
    private String updateDate;
}

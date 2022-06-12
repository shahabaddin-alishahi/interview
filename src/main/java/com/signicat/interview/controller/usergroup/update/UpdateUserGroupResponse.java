package com.signicat.interview.controller.usergroup.update;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class UpdateUserGroupResponse {

    private String id;
    private String title;
    private String createDate;
    private String updateDate;

}

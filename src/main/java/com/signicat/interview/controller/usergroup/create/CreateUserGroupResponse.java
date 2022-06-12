package com.signicat.interview.controller.usergroup.create;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class CreateUserGroupResponse {

    private String id;
    private String title;
    private String  createDate;
    private String updateDate;

}

package com.signicat.interview.controller.usergroup.create;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
public class CreateUserGroupRequest {

    @NotBlank
    private String title;

}

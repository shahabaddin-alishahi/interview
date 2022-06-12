package com.signicat.interview.controller.usergroup.create;

import com.signicat.interview.domain.Authority;
import com.signicat.interview.domain.UserGroup;
import com.signicat.interview.service.AuthorityService;
import com.signicat.interview.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@PreAuthorize("hasAuthority('USER_GROUP_CREATE')")
@RequiredArgsConstructor
@RestController
public class CreateUserGroupController {

    private final UserGroupService userGroupService;

    @PostMapping("${apis.secure}/user-group")
    public CreateUserGroupResponse handle(@RequestBody @Valid CreateUserGroupRequest request){

        UserGroup userGroup = userGroupService.createUserGroup(
                request.getTitle());

        return CreateUserGroupResponse.builder()
                .id(String.valueOf(userGroup.getId()))
                .createDate(String.valueOf(userGroup.getCreateDate()))
                .updateDate(String.valueOf(userGroup.getUpdateDate()))
                .title(userGroup.getTitle())
                .build();
    }
}

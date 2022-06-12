package com.signicat.interview.controller.usergroup.update;

import com.signicat.interview.domain.UserGroup;
import com.signicat.interview.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@PreAuthorize("hasAuthority('USER_GROUP_UPDATE')")
@RequiredArgsConstructor
@RestController
@Validated
public class UpdateUserGroupController {

    private final UserGroupService userGroupService;

    @PutMapping("${apis.secure}/user-group/{id}")
    public UpdateUserGroupResponse handle(@Pattern(regexp = "\\d{1,20}") @PathVariable String id,
                                          @RequestBody @Valid UpdateUserGroupRequest request){

        UserGroup userGroup = userGroupService.updateUserGroup(id , request.getTitle());

        return UpdateUserGroupResponse.builder()
                .id(String.valueOf(userGroup.getId()))
                .title(userGroup.getTitle())
                .createDate(String.valueOf(userGroup.getCreateDate()))
                .updateDate(String.valueOf(userGroup.getUpdateDate()))
                .build();
    }
}
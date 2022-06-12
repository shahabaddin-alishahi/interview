package com.signicat.interview.controller.usergroup.get;

import com.signicat.interview.domain.Authority;
import com.signicat.interview.domain.UserGroup;
import com.signicat.interview.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.eclipse.jetty.server.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

@PreAuthorize("hasAuthority('USER_GROUP_GET')")
@RequiredArgsConstructor
@RestController
@Validated
public class GetUserGroupController {

    private final UserGroupService userGroupService;

    @GetMapping("${apis.secure}/user-group/{id}")
    public GetUserGroupResponse handle(@Pattern(regexp = "\\d{1,20}") @PathVariable String id){

        UserGroup userGroup = userGroupService.findById(id);

        return GetUserGroupResponse.builder()
                .id(String.valueOf(userGroup.getId()))
                .title(userGroup.getTitle())
                .createDate(String.valueOf(userGroup.getCreateDate()))
                .updateDate(String.valueOf(userGroup.getUpdateDate()))
                .build();
    }
}

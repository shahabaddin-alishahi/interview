package com.signicat.interview.controller.usergroup.list;

import com.signicat.interview.config.security.OnlineUser;
import com.signicat.interview.domain.UserGroup;
import com.signicat.interview.service.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasAuthority('USER_GROUP_GET_LIST')")
@RequiredArgsConstructor
@RestController
public class GetUserGroupListController {

    private final UserGroupService userGroupService;

    @GetMapping("${apis.secure}/user-group/list")
    public UserGroupListResponse handle() {
        List<UserGroup> userGroupList = userGroupService.getAllUserGroupList();

        return UserGroupListResponse.builder()
                .items(userGroupList.stream().map(userGroup ->
                        UserGroupListResponse.UserGroupItem.builder()
                                .id(String.valueOf(userGroup.getId()))
                                .title(userGroup.getTitle())
                                .createDate(String.valueOf(userGroup.getCreateDate()))
                                .updateDate(String.valueOf(userGroup.getUpdateDate()))
                                .build()).collect(Collectors.toList()))
                .build();
    }
}

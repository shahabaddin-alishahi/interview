package com.signicat.interview.service;

import com.signicat.interview.domain.Authority;
import com.signicat.interview.domain.UserGroup;

import java.util.List;

public interface UserGroupService {

    List<UserGroup> getAllUserGroupList();

    UserGroup findById(String id);

    UserGroup createUserGroup(String title);

    UserGroup updateUserGroup(String id, String title);
}

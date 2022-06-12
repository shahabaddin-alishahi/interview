package com.signicat.interview.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserGroupServiceTest {

    @Autowired
    private UserGroupService userGroupService;

    @Test
    void getAllUserGroupList() {
        Assertions.assertEquals(0, userGroupService.getAllUserGroupList().size());
    }

    @Test
    void createUserGroup() {
        userGroupService.createUserGroup("test user group");
    }

}
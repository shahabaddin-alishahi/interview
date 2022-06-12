package com.signicat.interview.service.impl;

import com.signicat.interview.InterviewApplication;
import com.signicat.interview.service.AuthorityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthorityServiceImplTest {

    @Autowired
    private AuthorityService authorityService;

    @Test
    void getListOfAllAuthorities() {
        Assertions.assertTrue(authorityService.getListOfAllAuthorities().size() == 9);

    }
}
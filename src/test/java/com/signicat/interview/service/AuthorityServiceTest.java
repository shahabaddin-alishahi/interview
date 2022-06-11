//package com.signicat.interview.service;
//
//import com.signicat.interview.domain.Authority;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import static org.junit.jupiter.api.Assertions.*;
//
////@ActiveProfiles("test")
//@ExtendWith(SpringExtension.class)
//@SpringBootTest
//class AuthorityServiceTest {
//
//    @Autowired
//    private AuthorityService authorityService;
//
//    @Test
//    void getListOfAllAuthorities() {
//        Assertions.assertTrue(authorityService.getListOfAllAuthorities().size() == 9);
//    }
//
//    @Test
//    void findById() {
//        Authority authority = authorityService.findById("1");
//        Assertions.assertTrue(authority.getAuthority().equalsIgnoreCase("MEMBER_CREATE"));
//    }
//
//    @Test
//    void createAuthority() {
//        Authority authority = authorityService.createAuthority("test fro jupiter", "description test from jupiter for creating authority");
//        Assertions.assertEquals(authority, authorityService.findById(String.valueOf(authority.getId())));
//    }
//
//    @Test
//    void updateAuthority() {
//    }
//}
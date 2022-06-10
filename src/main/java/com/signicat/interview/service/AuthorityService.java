package com.signicat.interview.service;

import com.signicat.interview.domain.Authority;

import java.util.List;

public interface AuthorityService {

    List<Authority> getListOfAllAuthorities();

    Authority findById(String Id);
}

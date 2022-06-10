package com.signicat.interview.service.impl;

import com.signicat.interview.domain.Authority;
import com.signicat.interview.exception.AuthorityNotFoundException;
import com.signicat.interview.repository.AuthorityRepository;
import com.signicat.interview.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public List<Authority> getListOfAllAuthorities() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority findById(String Id) {
        return authorityRepository.findById(Long.parseLong(Id))
                .orElseThrow(AuthorityNotFoundException::new);
    }
}

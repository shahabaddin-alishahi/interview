package com.signicat.interview.controller.authority.create;

import com.signicat.interview.domain.Authority;
import com.signicat.interview.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@PreAuthorize("hasAuthority('AUTHORITY_CREATE')")
@RequiredArgsConstructor
@RestController
public class CreateAuthorityController {

    private final AuthorityService authorityService;

    @PostMapping("${apis.secure}/authority")
    public CreateAuthorityResponse handle(@RequestBody @Valid CreateAuthorityRequest request){

        Authority authority = authorityService.createAuthority(
                request.getTitle(),
                request.getDescription());

        return CreateAuthorityResponse.builder()
                .id(String.valueOf(authority.getId()))
                .createDate(String.valueOf(authority.getCreateDate()))
                .updateDate(String.valueOf(authority.getUpdateDate()))
                .title(authority.getTitle())
                .description(authority.getDescription())
                .build();
    }
}

package com.signicat.interview.controller.authority.get;

import com.signicat.interview.domain.Authority;
import com.signicat.interview.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;

@PreAuthorize("hasAuthority('AUTHORITY_GET')")
@RequiredArgsConstructor
@RestController
@Validated
public class GetAuthorityController {

    private final AuthorityService authorityService;

    @GetMapping("${apis.secure}/authority/{authorityId}")
    public GetAuthorityResponse handle(@Pattern(regexp = "\\d{1,20}") @PathVariable String authorityId){

        Authority authority = authorityService.findById(authorityId);

        return GetAuthorityResponse.builder()
                .id(String.valueOf(authority.getId()))
                .title(authority.getAuthority())
                .description(authority.getDescription())
                .createDate(String.valueOf(authority.getCreateDate()))
                .updateDate(String.valueOf(authority.getUpdateDate()))
                .build();
    }
}

package com.signicat.interview.controller.authority.update;

import com.signicat.interview.domain.Authority;
import com.signicat.interview.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@PreAuthorize("hasAuthority('AUTHORITY_UPDATE')")
@RequiredArgsConstructor
@RestController
@Validated
public class UpdateAuthorityController {

    private final AuthorityService authorityService;

    @PutMapping("${apis.secure}/authority/{authorityId}")
    public UpdateAuthorityResponse handle(@Pattern(regexp = "\\d{1,20}") @PathVariable String authorityId,
                                          @RequestBody @Valid UpdateAuthorityRequest request){

        Authority authority = authorityService.updateAuthority(authorityId , request.getTitle(), request.getDescription());

        return UpdateAuthorityResponse.builder()
                .id(String.valueOf(authority.getId()))
                .title(authority.getTitle())
                .description(authority.getDescription())
                .createDate(String.valueOf(authority.getCreateDate()))
                .updateDate(String.valueOf(authority.getUpdateDate()))
                .build();
    }
}
package com.signicat.interview.controller.authority.list;

import com.signicat.interview.config.security.OnlineUser;
import com.signicat.interview.controller.member.list.UsersListResponse;
import com.signicat.interview.domain.Authority;
import com.signicat.interview.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasAuthority('AUTHORITY_GET_LIST')")
@RequiredArgsConstructor
@RestController
public class GetAuthorityListController {

    private AuthorityService authorityService;

    @GetMapping("${apis.secure}/authority/list")
    public GetAuthorityListResponse handle() {
        List<Authority> authorityList = authorityService.getListOfAllAuthorities();

        return GetAuthorityListResponse.builder()
                .items(authorityList.stream().map(
                        authority -> GetAuthorityListResponse.AuthorityItem.builder()
                                .id(String.valueOf(authority.getId()))
                                .createDate(String.valueOf(authority.getCreateDate()))
                                .updateDate(String.valueOf(authority.getUpdateDate()))
                                .title(authority.getTitle())
                                .description(authority.getDescription())
                                .build()).collect(Collectors.toList())).build();

    }
}

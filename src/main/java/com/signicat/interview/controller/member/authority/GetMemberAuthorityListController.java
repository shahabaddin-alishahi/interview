package com.signicat.interview.controller.member.authority;

import com.signicat.interview.config.security.OnlineUser;
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
public class GetMemberAuthorityListController {

    private final OnlineUser onlineUser;

    @GetMapping("${apis.secure}/member/me/authorities")
    public List<String> handle() {
        return onlineUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}

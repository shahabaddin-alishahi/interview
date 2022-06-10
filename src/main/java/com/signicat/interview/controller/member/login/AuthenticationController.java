package com.signicat.interview.controller.member.login;

import com.signicat.interview.domain.AuthenticationResult;
import com.signicat.interview.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class AuthenticationController {

    private final MemberService memberService;

    @PostMapping("${apis.secure}/login")
    public AuthenticationResponse handle(@RequestBody @Valid AuthenticationRequest request){
        AuthenticationResult tokens = memberService.login(request.getUsername(), request.getPassword());
        return AuthenticationResponse.builder()
                .accessToken(tokens.getAccessToken())
                .refreshToken(tokens.getRefreshToken())
                .accessTokenExpirationTimeInMilliSeconds(tokens.getAccessTokenExpirationTimeInMilliSeconds())
                .refreshTokenExpirationTimeInMilliSeconds(tokens.getRefreshTokenExpirationTimeInMilliSeconds())
                .build();
    }
}

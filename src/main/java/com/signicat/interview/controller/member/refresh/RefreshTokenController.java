package com.signicat.interview.controller.member.refresh;

import com.signicat.interview.domain.AuthenticationResult;
import com.signicat.interview.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class RefreshTokenController {

    private final MemberService memberService;

    @PostMapping("${apis.secure}/refresh")
    public RefreshTokenResponse handle(@RequestBody @Valid RefreshTokenRequest request){
        AuthenticationResult tokens = memberService.refreshAccessToken(request.refreshToken);
        return RefreshTokenResponse.builder()
                .accessToken(tokens.getAccessToken())
                .accessTokenExpirationTimeInMilliSeconds(tokens.getAccessTokenExpirationTimeInMilliSeconds())
                .refreshToken(tokens.getRefreshToken())
                .refreshTokenExpirationTimeInMilliSeconds(tokens.getRefreshTokenExpirationTimeInMilliSeconds())
                .build();
    }
}

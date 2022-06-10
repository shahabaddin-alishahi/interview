package com.signicat.interview.controller.member.login;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationResponse {
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpirationTimeInMilliSeconds;
    private Long refreshTokenExpirationTimeInMilliSeconds;
}

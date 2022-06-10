package com.signicat.interview.controller.member.refresh;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RefreshTokenResponse {
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpirationTimeInMilliSeconds;
    private Long refreshTokenExpirationTimeInMilliSeconds;
}

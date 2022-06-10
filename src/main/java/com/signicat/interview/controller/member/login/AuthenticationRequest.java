package com.signicat.interview.controller.member.login;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}

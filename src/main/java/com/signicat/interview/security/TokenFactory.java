package com.signicat.interview.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;

import com.nimbusds.jose.JOSEObjectType;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.ECDSASigner;
import com.nimbusds.jose.jwk.ECKey;
import com.nimbusds.jwt.JWTClaimsSet;

import java.time.Instant;
import java.util.Date;

public class TokenFactory {
    private static final Long TOKEN_EXPIRATION_TIME = 600L;
    private final ECKey key;

    public TokenFactory(ECKey key) {
        this.key = key;
    }

    public String generate() throws JOSEException {
        SignedJWT signedJWT = new SignedJWT(createHeader(), createClaimsSet());
        signedJWT.sign(new ECDSASigner(key.toECPrivateKey()));
        return signedJWT.serialize();
    }

    private JWSHeader createHeader() {
        return new JWSHeader.Builder(JWSAlgorithm.ES256)
                .type(JOSEObjectType.JWT)
                .keyID(key.getKeyID())
                .build();
    }

    private JWTClaimsSet createClaimsSet() {
        return new JWTClaimsSet.Builder()
                .claim("key", "value")
                .expirationTime(
                        Date.from(Instant.now().plusSeconds(TOKEN_EXPIRATION_TIME))
                ).build();
    }
}

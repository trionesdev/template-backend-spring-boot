package com.ms;

import com.ms.core.conf.jwt.JwtProperties;
import com.ms.core.conf.jwt.JwtUtils;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class JwtTests {

    String sharedSecret ="12121212122222222222222222222222222222222222222222222212";

    @SneakyThrows
    @Test
    public void test() throws JOSEException {
        JWSSigner signer = new MACSigner(sharedSecret.getBytes(StandardCharsets.UTF_8));
        JWTClaimsSet.Builder jwtClaimsSetBuilder = new JWTClaimsSet.Builder()
                .subject("").claim("a","111") ;
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), jwtClaimsSetBuilder.build());
        signedJWT.sign(signer);
        String s = signedJWT.serialize();
        System.out.println(s);

        JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256), new Payload(signedJWT));
        jwsObject.sign(signer);
//        String s = jwsObject.serialize();
//        System.out.println(s);

        jwsObject = JWSObject.parse(s);

        JWSVerifier verifier = new MACVerifier(sharedSecret);


        System.out.println(SignedJWT.parse(s).getJWTClaimsSet().getClaims());
        System.out.println(jwsObject.getPayload().toJSONObject());
    }

    @Test
    public void test2(){
        JwtProperties jwtProperties = JwtProperties.builder().secret("222222212121232131232312313231231231212").build();
        Map<String,Object> cMap = new HashMap<>();
        cMap.put("userId","12121");
        String s = JwtUtils.generateToken(jwtProperties,"1",cMap);
        System.out.println(s);
        Map<String,Object> stringObjectMap = JwtUtils.parseClaims(jwtProperties,s);
        System.out.println(stringObjectMap);
    }

}

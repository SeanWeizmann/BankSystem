package com.sean.bankSystem.tokenVerifyLogin;

import com.sean.bankSystem.exceptions.InvalidException;
import com.sean.bankSystem.login.ClientType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.security.auth.login.LoginException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtUtil {


    @Autowired
    private ApplicationContext ctx;

    private final String inCodingAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    public byte[] codedKey = "not+a+musician+but+i+would+love+to+break+a+guitarOnStag".getBytes(StandardCharsets.UTF_8);
    private final Key decodedKey = new SecretKeySpec(Base64.getDecoder().decode(codedKey), inCodingAlgorithm);

    public String generateToken(String email, String password, ClientType clientType) throws LoginException {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userName", email);
        claims.put("clientType", clientType);
        return createToken(claims, email);
    }

    public void checkToken(String token, ClientType clientType) throws InvalidException {
        checkClientType(clientType, token);
        isTokenExpired(token);
    }

    private String createToken(Map<String, Object> claims, String subject) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
                .signWith(SignatureAlgorithm.HS256, codedKey)
                .compact();
    }


    public Claims extract(String token) throws InvalidException {
        try {
            JwtParser recognize = Jwts.parser().setSigningKey(codedKey);
            return recognize.parseClaimsJws(token).getBody();
        } catch (Throwable err) {
            throw new InvalidException("this mf not belong here");
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            extract(token);
            return true;
        } catch (Throwable e) {
            return false;
        }
    }

    public boolean checkClientType(ClientType clientType, String token) throws InvalidException {
        return ClientType.valueOf(extract(token).get("clientType").toString()) == clientType;
    }
}

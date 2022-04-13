package com.choo.blog.util;

import com.choo.blog.exceptions.InvalidParameterException;
import com.choo.blog.exceptions.InvalidTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
@Slf4j
public class WebTokenUtil {
    private Key key;

    public WebTokenUtil(@Value("${jwt.secret}") String secret){
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String encode(Long id){
        return Jwts.builder()
                .claim("userId", id)
                .signWith(key)
                .compact();
    }


    public Claims decode(String token) {
        log.debug("token : {token}", token);
        if (!checkValidToken(token)) {
            throw new InvalidTokenException(token);
        }
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            throw new InvalidTokenException(token);
        } catch (MalformedJwtException e){
            throw new InvalidTokenException(token);
        }
    }

    private boolean checkValidToken(String token) {
        return !(token == null || token.isEmpty());
    }
}

package com.lc.apilc.services;

import com.lc.apilc.models.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.signature}")
    private String signature;

    public String createToken(User user) {
        long expString = Long.parseLong(expiration);
        LocalDateTime expTime = LocalDateTime.now().plusMinutes(expString);
        Instant instant = expTime.atZone(ZoneId.systemDefault()).toInstant();
        Date date = Date.from(instant);

        return Jwts
                .builder()
                .setSubject(user.getLogin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, signature)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(signature)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isTokenValid(String token) {
        try {
            Claims claims = getClaims(token);
            Date expDate = claims.getExpiration();
            LocalDateTime time = expDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(time);
        } catch (Exception e) {
            return false;
        }
    }

    public String getUserLogin(String token) throws ExpiredJwtException {
        return (String) getClaims(token).getSubject();
    }

}

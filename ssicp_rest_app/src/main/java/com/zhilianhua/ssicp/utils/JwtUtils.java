package com.zhilianhua.ssicp.utils;

import com.zhilianhua.ssicp.entity.db.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

@Slf4j
public class JwtUtils {
    private static final String SECRET_KEY = "ahsdjkfhasdyfuiqnbzxvbsxiuquweroahkjsdfhaksdfjlasdjfoquweoruoqwieruoi";

    // Generate a secure signing key
    private static final Key SIGNING_KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;


    public static String generateToken(User user) {
        Claims claims = Jwts.claims();
        claims.put(RequestUtils.NICK_NAME_KEY, user.getNickName());
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRATION_TIME);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getName())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SIGNING_KEY)
                .compact();
    }

    public static Optional<JwtUser> getUser(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SIGNING_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            JwtUser jwtUser = new JwtUser();
            jwtUser.setUserName(claims.getSubject());
            jwtUser.setNickName(claims.get(RequestUtils.NICK_NAME_KEY, String.class));
            return Optional.of(jwtUser);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Data
    public static class JwtUser {
        private String userName;
        private String nickName;
    }
}

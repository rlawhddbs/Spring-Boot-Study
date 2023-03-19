package com.example.board.common.jwt;

import com.example.board.common.exception.CustomException;
import com.example.board.common.error.ErrorCode;
import com.example.board.domain.auth.entity.User;
import com.example.board.domain.auth.entity.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final String SECRET_KEY = "practice_rlawhddbs_auth_jwt_token";

    private static final Long ACCESS_TOKEN_EXPIRE = 1000L * 3600 * 3;  //3시간
    private static final Long REFRESH_TOKEN_EXPIRE = 1000L * 3600 * 6;  //6시간

    private final UserRepository userRepository;

    private Key getSignKey(String secretKey) {
        byte[] keyBytes = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(String userName, Long expDate, TokenType type) {

        Claims claims = Jwts.claims();
        claims.put("type", type);
        claims.put("userName", userName);

        Date now = new Date();

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + expDate))
                .signWith(getSignKey(SECRET_KEY), SignatureAlgorithm.HS256)
                .compact();
    }

    public User verifyToken(String token) {
        String userName = parseToken(token).get("userName").toString();
        return userRepository.findByUserName(userName)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND));
    }

    private Claims parseToken(String token) {
        if (token.isEmpty()) {
            throw new CustomException(ErrorCode.TOKEN_NOT_PROVIDED);
        }
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey(SECRET_KEY))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.TOKEN_EXPIRED);
        } catch (IllegalArgumentException e) {
            throw new CustomException(ErrorCode.TOKEN_NOT_PROVIDED);
        } catch (UnsupportedJwtException | MalformedJwtException e) {
            throw new CustomException(ErrorCode.INVALID_TOKEN);
        }
    }

    public String generateAccessToken(String userName) {
        return generateJwtToken(userName, ACCESS_TOKEN_EXPIRE, TokenType.ACCESS);
    }

    public String generateRefreshToken(String userName) {
        return generateJwtToken(userName, REFRESH_TOKEN_EXPIRE, TokenType.REFRESH);
    }

}

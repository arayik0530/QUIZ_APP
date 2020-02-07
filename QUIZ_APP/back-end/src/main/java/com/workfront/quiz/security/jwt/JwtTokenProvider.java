package com.workfront.quiz.security.jwt;


import com.workfront.quiz.entity.enums.UserRole;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class JwtTokenProvider {
    private static final String USER_ID = "id";
    private static final String USER_EMAIL = "email";
    private static final String USER_ROLES = "roles";

    private final UserDetailsService userDetailsService;
    @Value("${jwt.token.secret}")
    private String secret;
    @Value("${jwt.token.expired}")
    private long validityInMilliseconds;

    public JwtTokenProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(Long userId, String email, Set<UserRole> roleSet) {


        Map<String, Object> claimsMap = new HashMap<>();
        String[] roles = roleSet.stream()
                .map(role -> role.toString()).toArray(String[]::new);
        claimsMap.put(USER_ID, userId);
        claimsMap.put(USER_EMAIL, email);
        claimsMap.put(USER_ROLES, roles);
        Claims claims = Jwts.claims(claimsMap);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public Authentication getAuthentication(String token) {
        boolean validateToken = validateToken(token);
        if (validateToken) {

            //TODO add userInfoDto
            Long userId = getUserId(token);
            String email = getUsername(token);
            String[] userRoles = getUserRoles(token);
            JwtUser userDetails = new JwtUser(userId,email,null);
            return new PreAuthenticatedAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        } else throw new JwtAuthenticationException("");
    }

    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String[] getUserRoles(String token) {
        String[] roles = Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token).getBody().get(USER_ROLES, String[].class);
        return roles;
    }

    public Long getUserId(String token) {
        String id = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getId();
        return Long.parseLong(id);
    }

    public Optional<String> resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return Optional.of(bearerToken.substring(7, bearerToken.length()));
        }
        return Optional.empty();
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }

}

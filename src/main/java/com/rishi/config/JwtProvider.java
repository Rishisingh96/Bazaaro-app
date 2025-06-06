package com.rishi.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtProvider {
    SecretKey key = Keys.hmacShaKeyFor(JWT_CONSTANT.SECRET_KEY.getBytes());

    public String generateToken(Authentication auth){
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles  = populateAuthorities(authorities);

        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000)) //10 hours
                .claim("email", auth.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();
//        return null;
    }
    //
    public String getEmailFromJwtToken(String jwt) {

        jwt = jwt.substring(7); // Remove "Bearer " prefix
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(jwt).getBody();
//        String email = String.valueOf(claims.get("email"));
//        return email;
        //or
        return String.valueOf(claims.get("email"));
    }

   // This method used to populate authorities from GrantedAuthority collection
    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();
        for (GrantedAuthority authority : authorities) {
            auths.add(authority.getAuthority());
        }
        return String.join(",", auths);
    }
}

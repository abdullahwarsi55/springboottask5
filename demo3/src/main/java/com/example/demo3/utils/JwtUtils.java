/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo3.utils;

import com.example.demo3.entity.User;
import java.util.Date;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.nio.file.AccessDeniedException;

/**
 *
 * @author admin
 */

@Component
public class JwtUtils {

    private static String secret = "secret";
    private static long expiryDuration = 2 * 60;

    public String generateJwt(User user){

        long milliTime = System.currentTimeMillis();
        long expiryTime = milliTime + expiryDuration;

        Date issuedAt = new Date(milliTime);
        Date expiryAt = new Date(expiryTime);
        
        Claims claims = Jwts.claims()
                .setIssuer(user.getId().toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);

       
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Claims verify(String authorization) throws Exception {

        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
            return claims;
        } catch(Exception e) {
            throw new AccessDeniedException("Access Denied");
        }

    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vPerez.ProgramacionNCapasNov2025.service;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author digis
 */
public class JwtUtil {
    @Autowired
    private UserDetailsService userDetailsService;
    
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    public String generateToken(String email){
        return Jwts.builder().setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis()+360000))
                .signWith(SECRET_KEY)
                .compact();
                
    
    }
    
    public static String validateToken(String token){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch(JwtException ex){
            return null;
        }
    }
    
}

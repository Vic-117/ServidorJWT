/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vPerez.ProgramacionNCapasNov2025.Components;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author digis
 */
@Component
public class JwtUtil {

    
    
    
      private Key claveSecreta = Keys.hmacShaKeyFor("equipoalfabuenamaravillaondadinamitaescuadronlobo".getBytes());

//    private Key getSigningKey() {
//        // Convierte el String de la propiedad en una Key válida
//        byte[] keyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
    
    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+360000))
                .signWith(claveSecreta)
                .compact();
                
    
    }
    
     public String extractUsername(String token) {
         
//        return extractAllClaims(token).getSubject();
        return Jwts.parser()
                .setSigningKey(claveSecreta)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
               
    }
//    public boolean validateToken(String token,UserDetails userDetails){
//        try{
//           String subject = Jwts.parserBuilder()
//                    .setSigningKey( getSigningKey())
//                    .build()
//                    .parseClaimsJws(token)
//                    .getBody()
//                    .getSubject();
//           return subject.equals(userDetails.getUsername());
//        }catch(JwtException ex){
//            return false;
//        }
//    }
//    
//     private Claims extractAllClaims(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey( getSigningKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }

    

    
}

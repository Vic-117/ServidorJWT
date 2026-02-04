/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vPerez.ProgramacionNCapasNov2025.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vPerez.ProgramacionNCapasNov2025.JPA.Result;
import vPerez.ProgramacionNCapasNov2025.JPA.Usuario;
import vPerez.ProgramacionNCapasNov2025.Components.JwtUtil;

/**
 *
 * @author digis
 */
@RestController
@RequestMapping("/login")
public class LoginRestController {

    
//    @Autowired
    
    private JwtUtil jwtUtil;
    
    public LoginRestController(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }
    
    @PostMapping
    public ResponseEntity login(@RequestBody Usuario usuario ){
            Result result = new Result();
        try{
            String jwt = jwtUtil.generateToken(usuario.getEmail());
            result.StatusCode = 201;
            result.Correct = true;
            return ResponseEntity.ok(jwt);
        }catch(Exception ex){
            result.StatusCode = 400;
            result.Correct = false;
            result.ex = ex;
            result.ErrorMesagge = ex.getLocalizedMessage();
            return ResponseEntity.status(result.StatusCode).body("error");
        }
//        return null;
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vPerez.ProgramacionNCapasNov2025.RestController;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vPerez.ProgramacionNCapasNov2025.JPA.Result;
import vPerez.ProgramacionNCapasNov2025.JPA.Usuario;
import vPerez.ProgramacionNCapasNov2025.Components.JwtUtil;
import vPerez.ProgramacionNCapasNov2025.DAO.UsuarioJpaDAOImplementation;

/**
 *
 * @author digis
 */
@RestController
@RequestMapping("/login")
public class LoginRestController {

//    @Autowired
    private JwtUtil jwtUtil;
    private PasswordEncoder passEncoder;
    private UsuarioJpaDAOImplementation UsuarioJPAImplements;

    public LoginRestController(JwtUtil jwtUtil, PasswordEncoder passEncoder, UsuarioJpaDAOImplementation UsuarioJPAImplements) {
        this.jwtUtil = jwtUtil;
        this.passEncoder = passEncoder;
        this.UsuarioJPAImplements = UsuarioJPAImplements;

    }

    @PostMapping
    public ResponseEntity login(@RequestBody Usuario usuario) {
        Result result = new Result();
        try {
            Usuario usuarioAuth = (Usuario) UsuarioJPAImplements.getByEmail(usuario.getEmail()).Object;
            String password = usuarioAuth.getPassword();
            if (passEncoder.matches(usuario.getPassword(), password)) {

                result.Object = jwtUtil.generateToken(usuario.getEmail());
                result.Objects = new ArrayList<>();
                result.Objects.add(usuarioAuth);
                result.StatusCode = 201;
                result.Correct = true;
                return ResponseEntity.status(result.StatusCode).body(result);

            }
            result.StatusCode = 404;
            return ResponseEntity.status(result.StatusCode).body(result);
        } catch (Exception ex) {
            result.StatusCode = 500;
            result.Correct = false;
            result.ex = ex;
            result.ErrorMesagge = ex.getLocalizedMessage();
            return ResponseEntity.status(result.StatusCode).body(result);
        }

    }

}

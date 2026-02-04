/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vPerez.ProgramacionNCapasNov2025.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vPerez.ProgramacionNCapasNov2025.DAO.IUsuarioJPA;
import vPerez.ProgramacionNCapasNov2025.DAO.UsuarioJpaDAOImplementation;
import vPerez.ProgramacionNCapasNov2025.JPA.Usuario;

/**
 *
 * @author digis
 */
@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

    private UsuarioJpaDAOImplementation usuarioJPA;
    
    public void UserDetailsService( UsuarioJpaDAOImplementation usuarioJPA){
        this.usuarioJPA = usuarioJPA;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Usuario usuario = (Usuario)usuarioJPA.getByEmail(username).Object;
        
        boolean desactivado;
        desactivado = usuario.getEstatus() == 0 ? true:false;
        
        return User.withUsername(usuario.getEmail())
                .password(usuario.getPassword())
                .roles(usuario.rol.getNombre())
                .disabled(desactivado)
                .build();
        
        
    }
    
}

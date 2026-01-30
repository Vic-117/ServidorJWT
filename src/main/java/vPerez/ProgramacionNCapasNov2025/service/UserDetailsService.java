/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vPerez.ProgramacionNCapasNov2025.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import vPerez.ProgramacionNCapasNov2025.DAO.IUsuarioJPA;

/**
 *
 * @author digis
 */
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

    private IUsuarioJPA usuarioJPA;
    
    public void UserDetailsService( IUsuarioJPA usuarioJPA){
        this.usuarioJPA = usuarioJPA;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}

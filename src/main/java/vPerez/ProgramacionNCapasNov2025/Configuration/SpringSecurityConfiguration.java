/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vPerez.ProgramacionNCapasNov2025.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;
import vPerez.ProgramacionNCapasNov2025.Components.JwtFilter;

/**
 *
 * @author digis
 */
@Configuration
@EnableWebSecurity

public class SpringSecurityConfiguration {

//    private UserDetailsService userDetailsService;
    private JwtFilter jwtFilter;
//    private AuthenticationProvider authenticationProvider;

    public SpringSecurityConfiguration(JwtFilter jwtFilter) {

//        this.userDetailsService = userDetailsService;
        this.jwtFilter = jwtFilter;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.cors(corsCustomizer -> corsCustomizer.disable())
                //                .cors().disable()
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(config -> config
                .requestMatchers("/login").permitAll()
                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                .requestMatchers("/api/usuarios/*")
                .hasAnyAuthority("ROLE_Usuario","ROLE_Administrador")
//                .requestMatchers("/api/usuarios/busqueda").hasAnyAuthority("ROLE_Administrador")
                .requestMatchers("/api/usuarios/detalle/**","/api/rol/**", "/api/colonia/**", "/api/municipio/**", "/api/estado/**")
                .hasAnyAuthority("ROLE_Administrador", "ROLE_Usuario")
                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);//añade filtro para autorizacion antes de ejecutar(Token Json)
        
        return http.build();
        
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}

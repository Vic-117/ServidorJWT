/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vPerez.ProgramacionNCapasNov2025.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vPerez.ProgramacionNCapasNov2025.service.JwtFilter;

/**
 *
 * @author digis
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

    private UserDetailsService userDetailsService;
    private JwtFilter jwtService;
////    private CustomFailureHandler customFailureHandler;
//    private CustomSuccessHandler customSuccessHandler;

    public SpringSecurityConfiguration(UserDetailsService userDetailsService, JwtFilter jwtService) {
       
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }
    
    @Bean
    public SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(config -> config.requestMatchers("/login/**").permitAll().anyRequest().authenticated())
                .addFilterBefore(new JwtFilter(),UsernamePasswordAuthenticationFilter.class);
        
                
        return http.build();
    
    }

}

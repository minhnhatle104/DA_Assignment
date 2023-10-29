package com.doctoranywhere.lnminh_daassignment.security;

import com.doctoranywhere.lnminh_daassignment.security.filter.JwtFilter;
import com.doctoranywhere.lnminh_daassignment.security.provider.CustomAuthenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomAuthenProvider customAuthenProvider;

    @Autowired
    private JwtFilter jwtFilter;

    // Init standart encrpyt password and bring it to IOC
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // Redefine authentication manager using custom provider
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenProvider)
                .build();
    }

    // Config Security rules
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf().disable() //Disable csrf attack because using API
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // No using session
                .and()
                .authorizeHttpRequests()
                .antMatchers("/api/users/**").permitAll() // No need to sign in to use these API
                .antMatchers(HttpMethod.POST,"/api/tasks").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/tasks/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/tasks/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/tasks").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.GET,"/api/tasks/{id}").hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated() // any request left must be authenticated
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}

package com.jwt.jwt_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
//                It’s a web security vulnerability that tricks a logged-in user’s browser
//                into sending unauthorized requests to a web application
                .csrf(AbstractHttpConfigurer::disable)

                .authorizeHttpRequests(request -> request
                        .requestMatchers("/login").permitAll()
                        .anyRequest().authenticated())

//                HTTP request must carry its own authentication credentials (like a JWT token)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

//                HTTP Basic auth is a simple authentication scheme built into the HTTP protocol.
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}

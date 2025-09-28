package com.earts.SpringSecurity.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/*
@Configuration tells Spring this class contains configuration beans
@EnableWebSecurity enables Spring Security's web security features
The securityFilterChain method configures how your application handles security:
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        private final JwtFilter jwtFilter;

        return http
                .csrf(AbstractHttpConfigurer::disable)
                /*
                Disables CSRF protection.
                Useful when you're building a REST API, especially stateless ones where CSRF isn't needed (e.g., no cookies, sessions).
                 */

                .authorizeHttpRequests(request -> request
                        .requestMatchers("register", "login")
                        .permitAll()
                        .anyRequest().authenticated())
                /*
                This restricts access so every request must be authenticated.
                If the user is not logged in, they’ll be prompted for credentials.
                 */

                .httpBasic(Customizer.withDefaults())
                /*
                Enables HTTP Basic authentication.
                The browser will pop up a username/password dialog or you can use tools like Postman.
                Credentials are sent in the HTTP header.
                 */

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                /*
                ensures Spring doesn’t keep track of logged-in users with sessions.
                What it does: Makes the application stateless - no sessions are created or stored
                Why: Common for APIs where each request must include authentication credentials
                 */

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
//        http.formLogin(Customizer.withDefaults());


    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//            provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
            provider.setPasswordEncoder(new BCryptPasswordEncoder(8));
            provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails user1 = User
//                .withDefaultPasswordEncoder()
//                .username("senesh")
//                .password("1212")
//                .roles("USER")
//                .build();
//
//        UserDetails user2 = User
//                .withDefaultPasswordEncoder()
//                .username("dilshan")
//                .password("1313")
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }
}

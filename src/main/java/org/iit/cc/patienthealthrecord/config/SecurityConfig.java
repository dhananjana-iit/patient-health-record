package org.iit.cc.patienthealthrecord.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // Disable CSRF for simplicity
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/patients/health").permitAll() // Publicly accessible endpoint
                        .anyRequest().authenticated() // Protect all other endpoints
                )
                .httpBasic(); // Enable Basic Authentication

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Encoder for hashed passwords
    }
}

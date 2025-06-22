package com.rishi.config;

import jakarta.servlet.http.HttpServletRequest;
import jdk.jfr.consumer.RecordedThread;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


import java.util.Collections;

import static java.util.Collections.*;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       http.sessionManagement(management->management.sessionCreationPolicy(
               SessionCreationPolicy.STATELESS
               )).authorizeHttpRequests(authorize -> authorize
                       .requestMatchers("/api/**").authenticated()
                       .requestMatchers("/auth/**","/sellers/**").permitAll()
                       .requestMatchers("/api/products/*/reviews").permitAll()
                       .anyRequest().permitAll()
       ).addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
//               .csrf(csrc ->csrc.disable())  //or we can use lambda expression
               .csrf(AbstractHttpConfigurer::disable)
               .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    // CORS Configuration
    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(Collections.singletonList("*")); // Allow all origins
                configuration.setAllowedMethods(Collections.singletonList("*")); // Allow all methods
                configuration.setAllowedHeaders(Collections.singletonList("*")); // Allow all headers
                configuration.setAllowCredentials(true); // Allow credentials
                configuration.setExposedHeaders(Collections.singletonList("Authorization")); // Expose Authorization header
                configuration.setMaxAge(3600L); // Set max age for preflight requests

                return configuration;
            }
        };
    }


    // // Password Encoder Bean
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

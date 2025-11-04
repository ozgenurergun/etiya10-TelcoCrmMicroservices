package com.etiya.common.configuration;

import com.etiya.common.crosscuttingconcerns.exceptions.problemdetails.ProblemDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // YENİ: 401 Unauthorized (Kimlik Doğrulama) Hataları için
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            ProblemDetails problemDetails = new ProblemDetails();
            problemDetails.setTitle("Authentication Failed");
            problemDetails.setType("https://example.com/probs/authentication");
            problemDetails.setStatus(HttpStatus.UNAUTHORIZED.value());
            problemDetails.setDetail("You must be authenticated to access this resource: " + authException.getMessage());

            // ProblemDetails'i JSON olarak yaz
            new ObjectMapper().writeValue(response.getOutputStream(), problemDetails);
        };
    }

    // YENİ: 403 Forbidden (Yetkilendirme) Hataları için
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);

            ProblemDetails problemDetails = new ProblemDetails();
            problemDetails.setTitle("Authorization Failed");
            problemDetails.setType("https://example.com/probs/authorization");
            problemDetails.setStatus(HttpStatus.FORBIDDEN.value());
            problemDetails.setDetail("You are not authorized to access this resource: " + accessDeniedException.getMessage());

            // ProblemDetails'i JSON olarak yaz
            new ObjectMapper().writeValue(response.getOutputStream(), problemDetails);
        };
    }
}
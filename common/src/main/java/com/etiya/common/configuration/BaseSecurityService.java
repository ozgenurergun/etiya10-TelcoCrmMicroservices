package com.etiya.common.configuration;

import com.etiya.common.filters.JwtAuthFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint; // YENİ
import org.springframework.security.web.access.AccessDeniedHandler; // YENİ
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@Service
public class BaseSecurityService {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationEntryPoint authenticationEntryPoint; // YENİ
    private final AccessDeniedHandler accessDeniedHandler; // YENİ

    // Constructor'ı güncelle
    public BaseSecurityService(JwtAuthFilter jwtAuthFilter, AuthenticationEntryPoint authenticationEntryPoint, AccessDeniedHandler accessDeniedHandler) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
    }

    public static final String[] WHITE_LIST_URLS= {
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/api/auth/**",
            "/api/cities/**",
            "/api/districts/**",
            "/api/addresses/**",
            "/api/billingAccounts/**",
            "/api/contactmediums/**",
            "/api/individual-customers/**",
            "/api/customer-search/**",
            "/api/products/**",
            "/api/genel-types/**",
            "/api/genel-statuses/**",
            "/api/characteristics/**",
            "/api/char-values/**",
            "/api/product-specifications/**",
            "/api/product-spec-characteristics/**",
            "/api/product-char-values/**",
            "/api/campaigns/**",
            "/api/campaign-products/**",
            "/api/catalogs/**",
            "/api/product-offers/**",
            "/api/catalog-product-offers/**"
    };

    public HttpSecurity configureCoreSecurity(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exceptionHandling ->
                        exceptionHandling
                                .authenticationEntryPoint(authenticationEntryPoint)
                                .accessDeniedHandler(accessDeniedHandler)
                )
                // DÜZELTME: authorizeHttpRequests bloğunu buradan sildik.
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity;
    }
}
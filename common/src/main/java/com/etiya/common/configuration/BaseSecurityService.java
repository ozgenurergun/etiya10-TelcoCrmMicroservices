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

    public BaseSecurityService(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    private static final String[] WHITE_LIST_URLS= {
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
            "/api/campaigns/**",
            "/api/campaign-products/**",
            "/api/catalogs/**",
            "/api/product-offers/**",
            "/api/catalog-product-offers/**"
    };

    public HttpSecurity configureCoreSecurity(HttpSecurity httpSecurity) throws Exception{
        // CSRF Korumasını kapatma
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                // Beyaz Listedeki Yollara Herkese İzin Ver
                .authorizeHttpRequests(req->req.requestMatchers(WHITE_LIST_URLS).permitAll())
                // JWT Filtresini Güvenlik Zincirine Ekleme
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity;
    }
}
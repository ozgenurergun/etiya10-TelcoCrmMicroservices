package com.etiya.catalogservice.configuration;

import com.etiya.common.configuration.BaseSecurityService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final BaseSecurityService baseSecurityService;

    public SecurityConfig(BaseSecurityService baseSecurityService) {
        this.baseSecurityService = baseSecurityService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        // 1. Çekirdek ayarları (filtre, 401/403 handler) yükle
        baseSecurityService.configureCoreSecurity(httpSecurity);
        // 2. Kural zincirini TEK BİR BLOKTA tanımla
        httpSecurity.authorizeHttpRequests(req ->
                req.requestMatchers(BaseSecurityService.WHITE_LIST_URLS).permitAll() // Önce beyaz listeye izin ver
                        .anyRequest().authenticated() // Kalan tüm istekler kimlik doğrulaması istesin
        );
        return httpSecurity.build();
    }
}

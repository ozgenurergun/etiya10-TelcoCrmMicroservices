package com.etiya.basketservice.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class FeignClientConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                // 1. Mevcut (içeri gelen) isteği al
                // RequestContextHolder, o an işlenen thread'e ait HTTP isteği bilgilerini tutar.
                ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

                if (attributes != null) {
                    HttpServletRequest request = attributes.getRequest();

                    // 2. Mevcut request'ten "Authorization" header'ını (yani JWT'yi) al
                    String authHeader = request.getHeader("Authorization");

                    // 3. Header varsa, bu header'ı giden Feign isteğine (template) ekle
                    // Artık CustomerService veya CatalogService'e giden istekte token olacak.
                    if (authHeader != null && !authHeader.isEmpty()) {
                        template.header("Authorization", authHeader);
                    }
                }
            }
        };
    }
}
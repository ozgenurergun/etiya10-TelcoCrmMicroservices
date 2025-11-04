package com.etiya.common.filters;

import com.etiya.common.jwt.JwtService;
import io.jsonwebtoken.JwtException; // YENİ
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint; // YENİ
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final AuthenticationEntryPoint authenticationEntryPoint; // YENİ

    // Constructor'ı güncelle
    public JwtAuthFilter(JwtService jwtService, AuthenticationEntryPoint authenticationEntryPoint) {
        this.jwtService = jwtService;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String jwtHeader = request.getHeader("Authorization");

        if(jwtHeader!=null && jwtHeader.startsWith("Bearer ")){
            try { // YENİ: try-catch bloğu
                String jwt = jwtHeader.substring(7);
                String username = jwtService.extractUsername(jwt);
                List<String> roles = jwtService.extractRoles(jwt);
                List<SimpleGrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();

                if (jwtService.validateToken(jwt,username)){
                    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,null,authorities);
                    token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(token);
                }
            } catch (Exception e) {
                // YENİ: Token geçersizse (süresi dolmuş, bozuk vs.)
                // Hatayı 401 handler'a yönlendir.
                SecurityContextHolder.clearContext(); // Güvenlik bağlamını temizle

                // DÜZELTME: Hatayı bir Spring Security AuthenticationException'a sarmala
                // AuthenticationException abstract bir sınıf değilse doğrudan, değilse BadCredentialsException gibi bir alt sınıfı kullanın.
                // En temiz yol, anonim bir class oluşturmaktır:
                AuthenticationException authException = new AuthenticationException("Token Error: " + e.getMessage()) {};
                // VEYA daha spesifik bir alt sınıf kullanın:
                // AuthenticationException authException = new org.springframework.security.authentication.BadCredentialsException("Invalid Token: " + e.getMessage());

                authenticationEntryPoint.commence(request, response, authException);
                return; // Filtre zincirini durdur
            }
        }

        filterChain.doFilter(request,response);
    }
}
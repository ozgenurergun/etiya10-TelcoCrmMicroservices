package com.etiya.common.filters;


import com.etiya.common.crosscuttingconcerns.exceptions.types.AuthenticationException;
import com.etiya.common.crosscuttingconcerns.handlers.GlobalExceptionHandler;
import com.etiya.common.jwt.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final GlobalExceptionHandler globalExceptionHandler;

    public JwtAuthFilter(JwtService jwtService, GlobalExceptionHandler globalExceptionHandler) {
        this.jwtService = jwtService;
        this.globalExceptionHandler = globalExceptionHandler;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String jwtHeader = request.getHeader("Authorization");

        // 1️⃣ Token yoksa -> hiç kimlik doğrulaması yok, zincire devam (anonim istek olabilir)
        if (jwtHeader == null || !jwtHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2️⃣ Token varsa çıkar
        String jwt = jwtHeader.substring(7);

        // 3️⃣ Username çözülmezse -> 401
        String username;
        try {
            username = jwtService.extractUsername(jwt);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token geçersiz.");
            return;
        }

        if (username == null || username.isEmpty()) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token'dan kullanıcı adı alınamadı.");
            return;
        }

        // 4️⃣ Token doğrulama başarısızsa -> 401
        if (!jwtService.validateToken(jwt, username)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Geçersiz veya süresi dolmuş token.");
            return;
        }

        // 5️⃣ Token geçerliyse Authentication oluştur ve SecurityContext’e ekle
        List<String> roles = jwtService.extractRoles(jwt);
        List<SimpleGrantedAuthority> authorities =
                roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(username, null, authorities);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 6️⃣ Zincire devam
        filterChain.doFilter(request, response);
    }

}
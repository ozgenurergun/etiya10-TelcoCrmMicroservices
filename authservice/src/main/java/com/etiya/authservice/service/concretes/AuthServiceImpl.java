package com.etiya.authservice.service.concretes;

import com.etiya.authservice.domain.User;
import com.etiya.authservice.service.abstracts.AuthService;
import com.etiya.authservice.service.abstracts.UserService;
import com.etiya.authservice.service.dtos.LoginRequest;
import com.etiya.authservice.service.dtos.LoginResponse;
import com.etiya.authservice.service.dtos.RegisterUserRequest;
import com.etiya.common.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(JwtService jwtService, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void register(RegisterUserRequest request) {
        userService.add(request);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        LoginResponse loginResponse = new LoginResponse();
        Authentication authentication = authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
        if(!authentication.isAuthenticated())
            throw new RuntimeException("E posta veya şifre hatalı"); //RuntimeEx türü AuthenticationEx olacak.
        UserDetails user = userService.loadUserByUsername(request.getEmail());
        loginResponse.setJwtToken(jwtService.generateToken(user.getUsername(),user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList()));

        User currentUser = userService.getByEmail(request.getEmail());

        loginResponse.setFirstName(currentUser.getFirstName());

        return  loginResponse;
    }
}

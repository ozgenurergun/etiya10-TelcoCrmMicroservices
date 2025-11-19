package com.etiya.authservice.service.concretes;

import com.etiya.authservice.domain.User;
import com.etiya.authservice.repository.UserRepository;
import com.etiya.authservice.service.abstracts.UserService;
import com.etiya.authservice.service.dtos.RegisterUserRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void add(RegisterUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> ouser =  userRepository.findByEmail(email);
        User user = new User();
        user.setFirstName(ouser.get().getFirstName());
        user.setLastName(ouser.get().getLastName());
        return user;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()->new AccessDeniedException("Giriş başarısız"));
    }
}

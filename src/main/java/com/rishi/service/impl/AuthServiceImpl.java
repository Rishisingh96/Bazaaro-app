package com.rishi.service.impl;

import com.rishi.config.JwtProvider;
import com.rishi.domain.USER_ROLE;
import com.rishi.modal.Cart;
import com.rishi.modal.User;
import com.rishi.repository.CartRepository;
import com.rishi.repository.UserRepository;
import com.rishi.response.SignupRequest;
import com.rishi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CartRepository cartRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.cartRepository = cartRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public String createUser(SignupRequest req) {
    // Here you would typically save the user to a database and return a success message or user ID.

        User user = userRepository.findByEmail(req.getEmail());
        if(user == null){
            User createUser = new User();
            createUser.setEmail(req.getEmail());
            createUser.setFullName(req.getFullName());
            createUser.setRole(USER_ROLE.ROLE_CUSTOMER);
            createUser.setMobile("7800017055");
            createUser.setPassword(passwordEncoder.encode(req.getOtp())); // You should hash the password in a real application
            // You can generate password/OTP logic here

            user = userRepository.save(createUser);

            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
        }

        // Generate JWT token for the user
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(USER_ROLE.ROLE_CUSTOMER.toString()));

        Authentication authentication = new UsernamePasswordAuthenticationToken(req.getEmail(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }
}

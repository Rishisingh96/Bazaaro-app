package com.rishi.controller;


import com.rishi.modal.VerificationCode;
import com.rishi.repository.UserRepository;
import com.rishi.request.LoginOtpRequest;
import com.rishi.request.LoginRequest;
import com.rishi.response.ApiResponse;
import com.rishi.response.AuthResponse;
import com.rishi.response.SignupRequest;
import com.rishi.service.AuthService;

import com.rishi.domain.USER_ROLE;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository  userRepository;

    private final AuthService authService;

    public AuthController(UserRepository userRepository, AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {

        String jwt  =  authService.createUser(req);

        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setMessage("Register successfully");
        response.setRole(USER_ROLE.ROLE_CUSTOMER);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sent/login-signup-otp")
    public ResponseEntity<ApiResponse> setOtpHandler(
            @RequestBody LoginOtpRequest req) throws Exception {

        authService.setLoginOtp(req.getEmail(), req.getRole());

        ApiResponse response = new ApiResponse();
        response.setMessage("OTP sent successfully to your email");
        return ResponseEntity.ok(response);
    }


    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest request) throws Exception {

        AuthResponse response = authService.signing(request);

        return ResponseEntity.ok(response);
    }
}


/*
// Veryfication Code Request Handler
@PostMapping("/sent/login-signup-otp")
    public ResponseEntity<AuthResponse> setOtpHandler(
            @RequestBody VerificationCode req) throws Exception {

        authService.setLoginOtp(req.getEmail(), req.getRole());

        AuthResponse response = new AuthResponse();
        response.setMessage("OTP sent successfully to your email");
        return ResponseEntity.ok(response);
    }*/


//Create first Time User
/* @PostMapping("/signup")
    public ResponseEntity<User> createUserHandler(@RequestBody SignupRequest req) {
        User user = new User();
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());

        // You can generate password/OTP logic here

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(savedUser);
    }*/




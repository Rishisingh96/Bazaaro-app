package com.rishi.service;

import com.rishi.domain.USER_ROLE;
import com.rishi.request.LoginRequest;
import com.rishi.response.AuthResponse;
import com.rishi.response.SignupRequest;

public interface AuthService {

    void setLoginOtp(String email, USER_ROLE role) throws Exception;

    String createUser(SignupRequest req) throws Exception;

    AuthResponse signing(LoginRequest request) throws Exception;
}

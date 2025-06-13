package com.rishi.service;

import com.rishi.response.SignupRequest;

public interface AuthService {

    void setLoginOtp(String email) throws Exception;

    String createUser(SignupRequest req) throws Exception;
}

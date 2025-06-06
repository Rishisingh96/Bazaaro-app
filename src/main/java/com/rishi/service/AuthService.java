package com.rishi.service;

import com.rishi.response.SignupRequest;

public interface AuthService {
    String createUser(SignupRequest req);
}

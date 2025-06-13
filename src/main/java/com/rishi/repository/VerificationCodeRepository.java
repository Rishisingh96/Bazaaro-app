package com.rishi.repository;

import com.rishi.modal.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode , Long> {
    VerificationCode findByEmail(String email);
}

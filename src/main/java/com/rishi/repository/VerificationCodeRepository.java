package com.rishi.repository;

import com.rishi.modal.VerificationCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode , Long> {
    Optional<VerificationCode> findFirstByEmail(String email);
    Optional<VerificationCode> findFirstByOtp(String otp);
    
    // Deprecated: Use findFirstByEmail/findFirstByOtp instead to handle duplicates
    @Deprecated
    default VerificationCode findByEmail(String email) {
        return findFirstByEmail(email).orElse(null);
    }
    
    @Deprecated
    default VerificationCode findByOtp(String otp) {
        return findFirstByOtp(otp).orElse(null);
    }
}

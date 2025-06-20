package com.rishi.controller;

import com.rishi.config.JwtProvider;
import com.rishi.modal.Seller;
import com.rishi.modal.SellerReport;
import com.rishi.modal.VerificationCode;
import com.rishi.service.EmailService;
import com.rishi.service.SellerService;
import com.rishi.utils.OtpUtil;
import jakarta.mail.MessagingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rishi.repository.VerificationCodeRepository;
import com.rishi.request.LoginRequest;
import com.rishi.response.AuthResponse;
import com.rishi.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sellers")
public class SellerController {
    private final VerificationCodeRepository  verificationCodeRepository;
    private final AuthService authService;
    private final SellerService sellerService;
    private final EmailService emailService;
    private final JwtProvider jwtProvider;


    
    public ResponseEntity<AuthResponse> loginSeller(@RequestBody LoginRequest req) throws Exception{

        String otp = req.getOtp();
        String email = req.getEmail();

//        VerificationCode verificationCode = verificationCodeRepository.findByEmail(email);
//        if(verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())) {
//            throw new Exception("Vefication Wrong Otp............ " + otp);
//        }

        req.setEmail("seller_" + email);
        System.out.println(otp + " - " + email);
        AuthResponse authResponse = authService.signing(req);
        return ResponseEntity.ok(authResponse);
    }

    @PatchMapping("/verify/{otp}")
     public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception {

         VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);

         if(verificationCode == null || !verificationCode.getOtp().equals(otp)) {
             throw new Exception("Verification Failed: Invalid OTP provided.");
         }
         Seller seller = sellerService.verifyEmail(verificationCode.getEmail(), otp);
         return new ResponseEntity<>(seller, HttpStatus.OK);
     }

     public ResponseEntity<Seller> createSeller( @RequestBody Seller seller ) throws Exception, MessagingException{
        Seller savedSeller = sellerService.createSeller(seller);

        String otp = OtpUtil.generateOtp();


        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(seller.getEmail());
        verificationCodeRepository.save(verificationCode);

        String subject = "Rishi singh - Bazzaro app Email Verification Code ";
        String message = "Welcome to Bazzaro , verify your account using this link";
        String frontend_url = "http://localhost:3000/verify-seller/";
        emailService.sendVerificationOtpEmail(seller.getEmail(), verificationCode.getOtp(), subject, message + frontend_url + verificationCode.getOtp());
        return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
     }


     @GetMapping("/{id}")
     public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws Exception {
         Seller seller = sellerService.getSellerById(id);
         return new ResponseEntity<>(seller, HttpStatus.OK);
     }

     public ResponseEntity<Seller> getSellerByJwt(
             @RequestHeader("Authorization") String jwt) throws Exception {
         String email = jwtProvider.getEmailFromJwtToken(jwt);
         Seller seller = sellerService.getSellerByEmail(email);
         return new ResponseEntity<>(seller, HttpStatus.OK);
     }

   /*  @GetMapping("/report")
    public ResponseEntity<SellerReport> getSellerReport(@RequestHeader("Authorization") String jwt) throws Exception {
         String email = jwtProvider.getEmailFromJwtToken(jwt);
         Seller seller = sellerService.getSellerByEmail(email);
         SellerReport report =sellerReportService.getSellerReport(seller.getId());
         return new ResponseEntity<>(report, HttpStatus.OK);
     }*/

    @PatchMapping()
    public ResponseEntity<Seller> updateSeller(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Seller seller
    ) throws Exception{
        Seller profile = sellerService.getSellerProfile(jwt);
        Seller updatedSeller = sellerService.updateSeller(profile.getId(), seller);
        return ResponseEntity.ok(updatedSeller);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }
}

package com.rishi.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.rishi.modal.Seller;
import com.rishi.repository.SellerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rishi.config.JwtProvider;
import com.rishi.domain.USER_ROLE;
import com.rishi.modal.Cart;
import com.rishi.modal.User;
import com.rishi.modal.VerificationCode;
import com.rishi.repository.CartRepository;
import com.rishi.repository.UserRepository;
import com.rishi.repository.VerificationCodeRepository;
import com.rishi.request.LoginRequest;
import com.rishi.response.AuthResponse;
import com.rishi.response.SignupRequest;
import com.rishi.service.AuthService;
import com.rishi.service.EmailService;
import com.rishi.utils.OtpUtil;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartRepository cartRepository;
    private final JwtProvider jwtProvider;
    private final VerificationCodeRepository verificationCodeRepository;
    private final EmailService emailService;
    private final CustomUserServiceImpl customUserService;
    private final SellerRepository sellerRepository;


    @Override
    public void setLoginOtp(String email, USER_ROLE role) throws Exception {
        String SIGNING_PREFIX = "signing_";
//        String SELLER_PREFIX = "seller_";
        // Here you would typically generate an OTP and send it to the user's email.

        // Check if the email starts with the signing prefix
       if(email.startsWith(SIGNING_PREFIX)){
           email = email.substring(SIGNING_PREFIX.length());

           // Check if the user exists based on the role
           if(role.equals(USER_ROLE.ROLE_SELLER)){
               Seller seller = sellerRepository.findByEmail(email);
               if(seller == null){
                   throw new Exception("Seller does not exist with provided email....");
               }
           }
           else{
               User user = userRepository.findByEmail(email);
               if(user == null){
                   throw new Exception("User does not exist with provided email....");
               }
           }
       }

       // Check if a verification code already exists for the email
       VerificationCode isExist = verificationCodeRepository.findByEmail(email);

       if(isExist != null){
           verificationCodeRepository.delete(isExist);
       }

       String otp = OtpUtil.generateOtp();

       VerificationCode verificationCode = new VerificationCode();
         verificationCode.setOtp(otp);
         verificationCode.setEmail(email);
         verificationCodeRepository.save(verificationCode);

         String subject = "Rishi singh Login/Signup OTP";
            String body = "Your OTP for login/signup is: " + otp;

            emailService.sendVerificationOtpEmail(email,otp, subject, body);
    }

    @Override
    public String createUser(SignupRequest req) throws Exception {
    // Here you would typically save the user to a database and return a success message or user ID.

        // Validate the request
        VerificationCode verificationCode = verificationCodeRepository.findByEmail(req.getEmail());

        if(verificationCode == null || !verificationCode.getOtp().equals(req.getOtp())) {
            throw new Exception("Invalid OTP or user does not exist");
        }

        // Check if the user already exists
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

    @Override
    public AuthResponse signing(LoginRequest request) throws Exception {
        String username = request.getEmail();
        String otp = request.getOtp();
        // Validate the request
        Authentication authentication = authenticate(username, otp);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login successful");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roleName = authorities.isEmpty() ? null:authorities
                .iterator().next().getAuthority().toString();

        authResponse.setRole(USER_ROLE.valueOf(roleName));
        return authResponse;
    }

    private Authentication authenticate(String username, String otp) {
        UserDetails userDetails = customUserService.loadUserByUsername(username);

        if(userDetails == null){
            throw new BadCredentialsException("Invalid username ");
        }

        VerificationCode verificationCode = verificationCodeRepository.findByEmail(username);

        if(verificationCode == null || !verificationCode.getOtp().equals(otp)){
            throw new BadCredentialsException("User does not exist with provided email || Invalid username or password");
        }
        return  new UsernamePasswordAuthenticationToken(
               userDetails, 
               null, userDetails.getAuthorities()
        );
    }
}

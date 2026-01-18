package com.rishi.service.impl;

import java.util.List;

import com.rishi.exceptions.SellerException;
import com.rishi.modal.Address;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rishi.config.JwtProvider;
import com.rishi.domain.AccountStatus;
import com.rishi.domain.USER_ROLE;
import com.rishi.modal.Seller;
import com.rishi.repository.AddressRepository;
import com.rishi.repository.SellerRepository;
import com.rishi.service.SellerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final SellerRepository sellerRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    @Override
    public Seller getSellerProfile(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return this.getSellerByEmail(email);
    }

    @Override
    public Seller createSeller(Seller seller) {
        Seller sellerExist = sellerRepository.findFirstByEmail(seller.getEmail()).orElse(null);
        if(sellerExist != null) {
            throw new RuntimeException("Seller already exists with email: " + seller.getEmail());
        }

        Address saveAddress = addressRepository.save(seller.getPickupAddress());

        Seller newSeller = new Seller();

        newSeller.setSellerName(seller.getSellerName());
        newSeller.setMobile(seller.getMobile());
        newSeller.setEmail(seller.getEmail());
        newSeller.setPassword(passwordEncoder.encode(seller.getPassword()));

        newSeller.setBusinessDetails(seller.getBusinessDetails());
        newSeller.setBankDetails(seller.getBankDetails());
        newSeller.setPickupAddress(seller.getPickupAddress());
        newSeller.setGSTIN(seller.getGSTIN());
        newSeller.setRole(USER_ROLE.ROLE_SELLER);
//        newSeller.setEmailVerified(seller.isEmailVerified());
//        newSeller.setAccountStatus(seller.getAccountStatus());


        return sellerRepository.save(newSeller);
    }

    @Override
    public Seller getSellerById(Long id) throws SellerException {
        return sellerRepository.findById(id)
                .orElseThrow(()->new SellerException("Seller not found with id: " + id));
    }

    @Override
    public Seller getSellerByEmail(String email) throws SellerException {
        System.out.println("Searching for seller with email: " + email);
        // First try exact match
        Seller seller = sellerRepository.findFirstByEmail(email).orElse(null);
        
        // If not found, try case-insensitive search
        if (seller == null) {
            System.out.println("Exact match not found, trying case-insensitive search for: " + email);
            // Get all sellers and find case-insensitive match
            java.util.List<Seller> allSellers = sellerRepository.findAll();
            seller = allSellers.stream()
                .filter(s -> s.getEmail() != null && s.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
        }
        
        if (seller == null) {
            System.out.println("Seller not found with email: " + email);
            throw new SellerException("Seller not found with email: " + email + ". Please check if the email in database matches. " +
                "Note: Email matching is case-insensitive. Check for typos like .con vs .com");
        }
        
        System.out.println("Seller found: ID=" + seller.getId() + ", Email in DB=" + seller.getEmail() + ", Searched for=" + email);
        return seller;
    }

    @Override
    public List<Seller> getAllSellers(AccountStatus status) {
        if(status == null) {
            // If no status filter provided, return all sellers
            return sellerRepository.findAll();
        }
        // If status is provided, filter by that status
        return sellerRepository.findByAccountStatus(status);
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) throws Exception {
        Seller existingSeller = this.getSellerById(id);
        if(seller.getSellerName() != null) {
            existingSeller.setSellerName(seller.getSellerName());
        }
        if(seller.getMobile() != null) {
            existingSeller.setMobile(seller.getMobile());
        }
        if (seller.getEmail() != null) {
           existingSeller.setEmail(seller.getEmail());
        }
        if(seller.getBusinessDetails() != null
                && seller.getBusinessDetails().getBusinessName() != null) {
            existingSeller.getBusinessDetails().setBusinessName(
                    seller.getBusinessDetails().getBusinessName());
        }
        if(seller.getBankDetails() != null
                && seller.getBankDetails().getAccountNumber() != null
                && seller.getBankDetails().getIfscCode() != null
                && seller.getBankDetails().getAccountHolderName() != null
        ) {
            existingSeller.getBankDetails().setAccountHolderName(
                    seller.getBankDetails().getAccountHolderName());

            existingSeller.getBankDetails().setAccountNumber(
                    seller.getBankDetails().getAccountNumber());

            existingSeller.getBankDetails().setIfscCode(
                    seller.getBankDetails().getIfscCode());
        }

        if(seller.getPickupAddress() != null
                && seller.getPickupAddress().getAddress() != null
                && seller.getPickupAddress().getCity() != null
                && seller.getPickupAddress().getState() != null
                && seller.getPickupAddress().getMobile() != null
        ) {
            existingSeller.getPickupAddress().setAddress(
                    seller.getPickupAddress().getAddress());

            existingSeller.getPickupAddress().setCity(
                    seller.getPickupAddress().getCity());

            existingSeller.getPickupAddress().setState(
                    seller.getPickupAddress().getState());

            existingSeller.getPickupAddress().setMobile(
                    seller.getPickupAddress().getMobile());

        }
        if(seller.getGSTIN() != null) {
            existingSeller.setGSTIN(seller.getGSTIN());
        }
        return sellerRepository.save(existingSeller);
    }

    @Override
    public void deleteSeller(Long id) throws Exception {
        Seller seller = getSellerById(id);
        sellerRepository.delete(seller);
    }

    @Override
    public Seller verifyEmail(String email, String otp) throws Exception {
        System.out.println("Attempting to verify email: " + email);
        Seller seller = getSellerByEmail(email);
        seller.setEmailVerified(true);
        return sellerRepository.save(seller);
    }

    @Override
    public Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) throws Exception {
        Seller seller = getSellerById(sellerId);
        seller.setAccountStatus(status);
        return sellerRepository.save(seller);

    }
}

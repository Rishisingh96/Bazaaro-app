package com.rishi.service;

import com.rishi.domain.AccountStatus;
import com.rishi.modal.Seller;

import java.util.List;

public interface SellerService {
    Seller getSellerProfile(String jwt) throws Exception;
    Seller createSeller(Seller seller);
    Seller getSellerById(Long id) throws Exception;
    Seller getSellerByEmail(String email) throws Exception;
    List<Seller> getAllSellers(AccountStatus status);
    Seller updateSeller(Long id, Seller seller) throws Exception;
    void deleteSeller(Long id) throws Exception;
    Seller verifyEmail(String email, String otp) throws Exception;
    Seller updateSellerAccountStatus(Long sellerId, AccountStatus status) throws Exception;
}

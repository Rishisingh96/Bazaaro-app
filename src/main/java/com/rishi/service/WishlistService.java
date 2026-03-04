package com.rishi.service;

import com.rishi.modal.Product;
import com.rishi.modal.User;
import com.rishi.modal.Wishlist;

public interface WishlistService {
    Wishlist createWishList(User user);
    Wishlist getWishlistByUserId(User user);
    Wishlist addProductToWishList(User user, Product product);
}

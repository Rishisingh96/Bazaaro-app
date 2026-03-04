package com.rishi.controller;

import com.rishi.modal.Product;
import com.rishi.modal.User;
import com.rishi.modal.Wishlist;
import com.rishi.repository.WishlistRepository;
import com.rishi.service.ProductService;
import com.rishi.service.UserService;
import com.rishi.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wishlist")
public class WishlistController {
    private final WishlistRepository wishlistRepository;
    private final UserService userService;
    private final WishlistService wishlistService;
    private final ProductService productService;

    @GetMapping()
    public ResponseEntity<Wishlist> getWishlistByUserId(@RequestHeader("Authorization") String jwttoken) throws Exception {
        User user = userService.findUserByJwtToken(jwttoken);
        Wishlist wishlist = wishlistService.getWishlistByUserId(user);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping("/add-product/{productId}")
    public ResponseEntity<Wishlist> addProductToWishlist(@PathVariable Long productId,
                                                         @RequestHeader("Authorization") String jwttoken) throws Exception {
        Product product = productService.findProductById(productId);
        User user = userService.findUserByJwtToken(jwttoken);
        Wishlist updatedWishlist = wishlistService.addProductToWishList(
                user,
                product
        );
        return ResponseEntity.ok(updatedWishlist);
    }
}

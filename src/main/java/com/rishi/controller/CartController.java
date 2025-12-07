package com.rishi.controller;

import com.rishi.exceptions.ProductException;
import com.rishi.modal.Cart;
import com.rishi.modal.CartItem;
import com.rishi.modal.Product;
import com.rishi.modal.User;
import com.rishi.request.AddItemRequest;
import com.rishi.response.ApiResponse;
import com.rishi.service.CartItemService;
import com.rishi.service.CartService;
import com.rishi.service.ProductService;
import com.rishi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;
    private final UserService userService;
    private final ProductService productService;


    @GetMapping
    public ResponseEntity<Cart> findUserCartHandler(
            @RequestHeader("Authorization") String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findUserCart(user);

        return new ResponseEntity<Cart>(cart, HttpStatus.OK);
    }


    @PutMapping("/add")
    public ResponseEntity<CartItem> addItemToCart(
            @RequestBody AddItemRequest req,
            @RequestHeader("Authorization") String jwt)
            throws ProductException, Exception
    {
        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.findProductById(req.getProductId());

        CartItem item = cartService.addCartItem(
                user,
                product,
                req.getSize(),
                req.getQuantity()
        );

        ApiResponse response = new ApiResponse();
        response.setMessage("Items added To Cart Successfully...");

        return new ResponseEntity<>(item, HttpStatus.ACCEPTED);
    }


    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse>deleteCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization") String jwt)
        throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse response = new ApiResponse();
        response.setMessage("Item Remove From Cart...");

        return new ResponseEntity<ApiResponse>(response,  HttpStatus.ACCEPTED);
    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItem>updateCartItemHandler(
            @PathVariable Long cartItemId,
            @RequestBody CartItem cartItem,
            @RequestHeader("Authorization")String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        CartItem updatedCartItem = null;
        if (cartItem.getQuantity() > 0) {
            updatedCartItem = cartItemService.updatedCartItem(
                    user.getId(),
                    cartItemId, cartItem);

        }

        return new ResponseEntity<>(updatedCartItem,HttpStatus.ACCEPTED );
    }
}
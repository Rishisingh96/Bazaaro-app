package com.rishi.controller;

import com.razorpay.PaymentLink;
import com.rishi.domain.PaymentMethod;
import com.rishi.modal.*;
import com.rishi.repository.PaymentOrderRepository;
import com.rishi.response.PaymentLinkResponse;
import com.rishi.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;
    private final SellerService sellerService;
    private final SellerReportService sellerReportService;
    private final PaymentService paymentService;
    private final PaymentOrderRepository paymentOrderRepository;

    public ResponseEntity<PaymentLinkResponse> createOrderHandler(
            @RequestBody Address sphippingAddress,
            @RequestParam PaymentMethod paymentMethod,
            @RequestHeader("Authorization") String jwt) throws Exception
    {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findUserCart(user);
        Set<Order> orders = orderService.createOrder(user, sphippingAddress, cart);

        PaymentOrder paymentOrder = paymentService.createOrder(user, orders);

        PaymentLinkResponse response = new PaymentLinkResponse();

        if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
            PaymentLink payment = paymentService.createRazorpayPaymentLink(user,
                    paymentOrder.getAmount(),
                    paymentOrder.getId());
            String paymentUrl = payment.get("short_url");
            String paymentUrlId = payment.get("id");

            response.setPayment_link_url(paymentUrl);
            response.setPayment_link_id(paymentUrlId);

            paymentOrder.setPaymentLinkId(paymentUrlId);
            paymentOrderRepository.save(paymentOrder);
        }else {
            String paymentUrl = paymentService.createStripePaymentLink(user,
                    paymentOrder.getAmount(),
                    paymentOrder.getId());
            response.setPayment_link_url(paymentUrl);
        }
            return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistoryHandler(
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.userOrderHistory(user.getId());
        return new ResponseEntity<>(orders,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrderById(
            @PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.findOrderById(orderId);
        return new ResponseEntity<>(order,HttpStatus.ACCEPTED);
     }

     @GetMapping("/item/{orderItemId}")
     public ResponseEntity<OrderItem> getOrderItemById(
             @PathVariable Long orderItemId,
             @RequestHeader("Authorization") String jwt
     ) throws Exception {
        System.out.println("Order Item Controller: " );
        User user = userService.findUserByJwtToken(jwt);
        OrderItem orderItem = orderService.getOrderItemById(orderItemId);
        return new ResponseEntity<>(orderItem,HttpStatus.ACCEPTED);
     }

     @PutMapping("/{orderId}/cancel")
     public ResponseEntity<Order> cancelOrderHandler(
             @PathVariable Long orderId,
             @RequestHeader("Authorization") String jwt
     ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order cancelledOrder = orderService.cancelOrder(orderId,user);

        Seller seller = sellerService.getSellerById(cancelledOrder.getSellerId());
        SellerReport report = sellerReportService.getSellerReport(seller);

        report.setCancelledOrders(report.getCancelledOrders()+1);
        report.setTotalRefunds(report.getTotalRefunds()+ cancelledOrder.getTotalSellingPrice());
        sellerReportService.updateSellerReport(report);

        return new ResponseEntity<>(cancelledOrder,HttpStatus.ACCEPTED);
     }

}

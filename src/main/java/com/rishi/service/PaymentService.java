package com.rishi.service;

import com.razorpay.PaymentLink;
import com.rishi.modal.Order;
import com.rishi.modal.PaymentOrder;
import com.rishi.modal.User;

import java.util.Set;

public interface PaymentService {
    PaymentOrder createOrder(User user, Set<Order> orders);
    PaymentOrder getPaymentOrderById(Long orderId) throws Exception;
    PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception;
//    PaymentOrder updatePaymentOrderStatus(String orderId, String paymentId, String status) throws Exception;

    Boolean ProceedPaymentOrder(PaymentOrder paymentOrder,
                                String paymentId,
                                String paymentLinkId) throws Exception;

    PaymentLink createRazorpayPaymentLink(User user, Long amount, Long orderId) throws Exception;

    String createStripePaymentLink(User user, Long amount, Long orderId) throws Exception;
}

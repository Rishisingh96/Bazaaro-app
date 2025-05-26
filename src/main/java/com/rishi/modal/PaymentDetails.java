package com.rishi.modal;

import com.rishi.domain.PaymentStatus;
import lombok.Data;

@Data
public class PaymentDetails {
    private String paymentId;                   // Unique ID for the payment
    private String razorpayPaymentLinkId;        // Razorpay payment link ID
    private String razorpayPaymentLinkReferenceId; // Reference ID for the payment link
    private String razorpayPaymentLinkStatus;    // Status of the Razorpay payment link
    private String razorpayPaymentIdWSP;         // Razorpay Payment ID with WSP (custom field)
    private PaymentStatus status;                // Current status of the payment
}

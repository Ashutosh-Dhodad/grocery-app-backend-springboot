package com.ecommerce.backend.Payment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.RazorpayException;
import com.razorpay.Utils;

@RestController
@RequestMapping("/api/order")
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            logger.info("In order creation, amount: {}", orderRequest.getAmount());
            
            if (orderRequest.getAmount() <= 0) {
                return ResponseEntity.badRequest().body("Amount must be greater than zero.");
            }
            
            String orderId = paymentService.createOrder(orderRequest.getAmount(), "INR", "receipt#123");
            return ResponseEntity.ok(orderId);
        } catch (RazorpayException e) {
            logger.error("RazorpayException occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while processing payment: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create order: " + e.getMessage());
        }
    }



    @PostMapping("/verifyPayment")
    public String verifyPayment(@RequestBody PaymentVerificationRequest paymentVerificationRequest) {
        try {
            String orderId = paymentVerificationRequest.getRazorpayOrderId();
            String paymentId = paymentVerificationRequest.getRazorpayPaymentId();
            String signature = paymentVerificationRequest.getRazorpaySignature();

            // Create a signature string
            String payload = orderId + "|" + paymentId;

            // Verify the signature
            boolean isSignatureValid = Utils.verifySignature(payload, signature, "owKJJeszfwE6YD6DToishFuH");

            if (isSignatureValid) {
                // Payment is verified
                return "Payment Verified";
            } else {
                // Payment verification failed
                return "Payment Verification Failed";
            }
        } catch (RazorpayException e) {
            e.printStackTrace();
            return "Payment Verification Failed";
        }
    }
}

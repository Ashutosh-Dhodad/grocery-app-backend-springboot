package com.ecommerce.backend.Payment;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.razorpay.RazorpayException;



@Service
public class PaymentService {

    public String createOrder(int amount, String currency, String receipt) throws RazorpayException {
        String apiKey = "rzp_test_haDRsJIQo9vFPJ";
        String apiSecret = "owKJJeszfwE6YD6DToishFuH";

        String auth = apiKey + ":" + apiSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodedAuth);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("amount", amount);
        requestBody.put("currency", currency);
        requestBody.put("receipt", receipt);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(
            "https://api.razorpay.com/v1/orders", request, String.class
        );

        return response.getBody();
    }
}

package com.banking.paymentgateway.controller;

import com.banking.paymentgateway.model.*;
import com.banking.paymentgateway.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {
    private final PaymentService service;

    public PaymentController(PaymentService service) { this.service = service; }

    @PostMapping("/payments")
    public ResponseEntity<Payment> processPayment(@RequestBody Map<String, Object> req) {
        return ResponseEntity.ok(service.processPayment(
            (String)req.get("merchantId"), (String)req.get("customerId"),
            new BigDecimal(req.get("amount").toString()), (String)req.get("currency"),
            (String)req.get("cardLastFour")));
    }

    @GetMapping("/payments")
    public ResponseEntity<List<Payment>> getPayments(@RequestParam(required = false) String status) {
        return ResponseEntity.ok(service.getPayments(status));
    }

    @GetMapping("/payments/{id}")
    public ResponseEntity<Payment> getPayment(@PathVariable String id) {
        return ResponseEntity.ok(service.getPayment(id));
    }

    @PostMapping("/payments/{id}/refund")
    public ResponseEntity<Payment> refundPayment(@PathVariable String id) {
        return ResponseEntity.ok(service.refundPayment(id));
    }

    @PostMapping("/merchants")
    public ResponseEntity<Merchant> registerMerchant(@RequestBody Map<String, String> req) {
        return ResponseEntity.ok(service.registerMerchant(req.get("name"), req.get("callbackUrl")));
    }

    @GetMapping("/merchants")
    public ResponseEntity<List<Merchant>> getMerchants() {
        return ResponseEntity.ok(service.getMerchants());
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> health() {
        return ResponseEntity.ok(Map.of("status", "UP", "service", "payment-gateway"));
    }
}

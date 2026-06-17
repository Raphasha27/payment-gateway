package com.banking.paymentgateway.service;

import com.banking.paymentgateway.model.*;
import com.banking.paymentgateway.repository.*;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepo;
    private final MerchantRepository merchantRepo;

    public PaymentService(PaymentRepository pr, MerchantRepository mr) {
        this.paymentRepo = pr; this.merchantRepo = mr;
    }

    public Payment processPayment(String merchantId, String customerId, BigDecimal amount,
                                   String currency, String cardLastFour) {
        Payment payment = new Payment(merchantId, customerId, amount, currency, cardLastFour);
        payment = paymentRepo.save(payment);

        String transactionId = "TXN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        payment.setStatus("COMPLETED");
        payment.setTransactionId(transactionId);
        return paymentRepo.save(payment);
    }

    public Payment refundPayment(String paymentId) {
        Payment payment = paymentRepo.findById(paymentId).orElseThrow();
        payment.setStatus("REFUNDED");
        return paymentRepo.save(payment);
    }

    public Merchant registerMerchant(String name, String callbackUrl) {
        String apiKey = "key-" + UUID.randomUUID().toString().replace("-", "").substring(0, 16);
        return merchantRepo.save(new Merchant(name, apiKey, callbackUrl));
    }

    public List<Payment> getPayments(String status) {
        return status != null ? paymentRepo.findByStatus(status) : paymentRepo.findAll();
    }

    public Payment getPayment(String id) { return paymentRepo.findById(id).orElseThrow(); }

    public List<Merchant> getMerchants() { return merchantRepo.findAll(); }
}

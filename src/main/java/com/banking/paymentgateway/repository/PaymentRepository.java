package com.banking.paymentgateway.repository;

import com.banking.paymentgateway.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, String> {
    List<Payment> findByMerchantId(String merchantId);
    List<Payment> findByCustomerId(String customerId);
    List<Payment> findByStatus(String status);
}

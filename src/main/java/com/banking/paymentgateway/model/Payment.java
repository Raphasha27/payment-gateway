package com.banking.paymentgateway.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Table(name = "payments")
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String merchantId;
    private String customerId;
    private BigDecimal amount;
    private String currency;
    private String cardLastFour;
    private String status = "PENDING";
    private String transactionId;
    private LocalDateTime createdAt;
    private LocalDateTime processedAt;

    public Payment() {}
    public Payment(String merchantId, String customerId, BigDecimal amount, String currency, String cardLastFour) {
        this.merchantId = merchantId; this.customerId = customerId;
        this.amount = amount; this.currency = currency; this.cardLastFour = cardLastFour;
        this.createdAt = LocalDateTime.now();
    }
    public String getId() { return id; }
    public String getMerchantId() { return merchantId; }
    public String getCustomerId() { return customerId; }
    public BigDecimal getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getCardLastFour() { return cardLastFour; }
    public String getStatus() { return status; }
    public String getTransactionId() { return transactionId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getProcessedAt() { return processedAt; }
    public void setStatus(String s) { this.status = s; }
    public void setTransactionId(String t) { this.transactionId = t; this.processedAt = LocalDateTime.now(); }
}

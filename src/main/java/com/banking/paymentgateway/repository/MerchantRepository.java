package com.banking.paymentgateway.repository;

import com.banking.paymentgateway.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MerchantRepository extends JpaRepository<Merchant, String> {
    Optional<Merchant> findByApiKey(String apiKey);
}

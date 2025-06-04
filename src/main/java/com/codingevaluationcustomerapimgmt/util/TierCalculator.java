package com.codingevaluationcustomerapimgmt.util;

import com.codingevaluationcustomerapimgmt.model.enttites.Customer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.codingevaluationcustomerapimgmt.model.enttites.Tier.GOLD;
import static com.codingevaluationcustomerapimgmt.model.enttites.Tier.PLATINUM;
import static com.codingevaluationcustomerapimgmt.model.enttites.Tier.SILVER;

@Component
public class TierCalculator {
    public String calculateTier(Customer customer) {
        if (Objects.isNull(customer)) return null;
        BigDecimal amountSpend = customer.getAnnualSpend();
        LocalDateTime lastPurchaseDate = customer.getLastPurchaseDate();
        if (Objects.isNull(amountSpend)) {
            return SILVER.name();
        }
        if (amountSpend.compareTo(BigDecimal.valueOf(10000)) >= 0 && Objects.nonNull(lastPurchaseDate) && lastPurchaseDate.isAfter(LocalDateTime.now().minusMonths(6))) {
            return PLATINUM.name();
        }
        if (amountSpend.compareTo(BigDecimal.valueOf(1000)) >= 0 && Objects.nonNull(lastPurchaseDate) && lastPurchaseDate.isAfter(LocalDateTime.now().minusMonths(12))) {
            return GOLD.name();
        }
        return SILVER.name();
    }
}

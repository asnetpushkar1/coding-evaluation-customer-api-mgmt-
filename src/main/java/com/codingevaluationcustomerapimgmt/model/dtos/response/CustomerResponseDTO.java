package com.codingevaluationcustomerapimgmt.model.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CustomerResponseDTO(
        UUID id,
        String name,
        String email,
        BigDecimal annualSpend,
        LocalDateTime lastPurchaseDate,
        String tier) {

}

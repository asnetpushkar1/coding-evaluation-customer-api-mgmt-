package com.codingevaluationcustomerapimgmt.model.dtos.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CustomerRequestDTO(
        @NotBlank String name,
        @NotBlank @Email String email,
        BigDecimal annualSpend,
        LocalDateTime lastPurchaseDate
) {}



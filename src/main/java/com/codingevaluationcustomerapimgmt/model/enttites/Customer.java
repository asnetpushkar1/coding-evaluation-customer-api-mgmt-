package com.codingevaluationcustomerapimgmt.model.enttites;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue
    private UUID id;
    @NotBlank
    private String name;
    @Email
    @NotBlank
    private String email;
    private BigDecimal annualSpend;
    private LocalDateTime lastPurchaseDate;
}

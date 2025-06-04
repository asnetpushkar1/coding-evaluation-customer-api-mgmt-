package com.codingevaluationcustomerapimgmt.mapper;

import com.codingevaluationcustomerapimgmt.model.dtos.request.CustomerRequestDTO;
import com.codingevaluationcustomerapimgmt.model.dtos.response.CustomerResponseDTO;
import com.codingevaluationcustomerapimgmt.model.enttites.Customer;
import com.codingevaluationcustomerapimgmt.util.TierCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CustomerMapper {

    @Autowired
    TierCalculator tierCalculator;

    public Customer toEntity(CustomerRequestDTO dto) {
        if (Objects.isNull(dto)) return null;
        Customer customer = new Customer();
        customer.setName(dto.name());
        customer.setEmail(dto.email());
        customer.setAnnualSpend(dto.annualSpend());
        customer.setLastPurchaseDate(dto.lastPurchaseDate());
        return customer;
    }


    public CustomerResponseDTO toResponse(Customer customer) {
        if (Objects.isNull(customer)) return null;
        return new CustomerResponseDTO(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getAnnualSpend(),
                customer.getLastPurchaseDate(),
                tierCalculator.calculateTier(customer)
        );
    }
}


package com.codingevaluationcustomerapimgmt.service;


import com.codingevaluationcustomerapimgmt.model.dtos.request.CustomerRequestDTO;
import com.codingevaluationcustomerapimgmt.model.dtos.response.CustomerResponseDTO;

import java.util.UUID;

public interface CustomerService {
    CustomerResponseDTO getCustomerByEmail(String email);
    CustomerResponseDTO getCustomerById(UUID id);
    CustomerResponseDTO findCustomerByName(String name);
    CustomerResponseDTO create(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO updateCustomerById(UUID id, CustomerRequestDTO customerRequestDTO);
    void deleteCustomerById(UUID id);
}

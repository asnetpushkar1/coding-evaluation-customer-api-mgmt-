package com.codingevaluationcustomerapimgmt.service;

import com.codingevaluationcustomerapimgmt.exception.ResourceNotFoundException;
import com.codingevaluationcustomerapimgmt.mapper.CustomerMapper;
import com.codingevaluationcustomerapimgmt.model.dtos.request.CustomerRequestDTO;
import com.codingevaluationcustomerapimgmt.model.dtos.response.CustomerResponseDTO;
import com.codingevaluationcustomerapimgmt.model.enttites.Customer;
import com.codingevaluationcustomerapimgmt.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    public static final String CUSTOMER_WITH_ID_NOT_FOUND = "customer with id:%s not found";

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public CustomerResponseDTO getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(customerMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("customer with email:%s not found", email)));
    }

    @Override
    public CustomerResponseDTO getCustomerById(UUID id) {
        return customerRepository.findById(id)
                .map(customerMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(CUSTOMER_WITH_ID_NOT_FOUND, id)));
    }

    @Override
    public CustomerResponseDTO findCustomerByName(String name) {
        return customerRepository.findCustomerByName(name)
                .map(customerMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("customer with name:%s not found", name)));
    }

    @Override
    public CustomerResponseDTO create(CustomerRequestDTO customerRequestDTO) {
        return customerMapper
                .toResponse(customerRepository.save(customerMapper.toEntity(customerRequestDTO)));

    }

    @Override
    public CustomerResponseDTO updateCustomerById(UUID id, CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format(CUSTOMER_WITH_ID_NOT_FOUND, id)));
        customer.setEmail(customerRequestDTO.email());
        customer.setLastPurchaseDate(customerRequestDTO.lastPurchaseDate());
        customer.setName(customerRequestDTO.name());
        customer.setAnnualSpend(customerRequestDTO.annualSpend());
        return customerMapper.toResponse(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomerById(UUID id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format(CUSTOMER_WITH_ID_NOT_FOUND, id)));
        customerRepository.delete(customer);
    }
}

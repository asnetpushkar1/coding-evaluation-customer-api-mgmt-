
package com.codingevaluationcustomerapimgmt.service;

import com.codingevaluationcustomerapimgmt.exception.ResourceNotFoundException;
import com.codingevaluationcustomerapimgmt.mapper.CustomerMapper;
import com.codingevaluationcustomerapimgmt.model.dtos.request.CustomerRequestDTO;
import com.codingevaluationcustomerapimgmt.model.dtos.response.CustomerResponseDTO;
import com.codingevaluationcustomerapimgmt.model.enttites.Customer;
import com.codingevaluationcustomerapimgmt.repository.CustomerRepository;
import com.codingevaluationcustomerapimgmt.util.TierCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerMapper customerMapper;

    @Mock
    TierCalculator tierCalculator;

    @InjectMocks
    CustomerServiceImpl customerService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomerByEmailFound() {
        String email = "test@example.com";
        Customer customer = new Customer();
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(null, "", email, BigDecimal.valueOf(100), LocalDateTime.now(), "GOLD");
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.of(customer));
        when(customerMapper.toResponse(any(Customer.class))).thenReturn(responseDTO);

        CustomerResponseDTO result = customerService.getCustomerByEmail(email);
        assertEquals(responseDTO, result);
    }

    @Test
    void testGetCustomerByEmailNotFound() {
        when(customerRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> customerService.getCustomerByEmail("notfound@example.com"));
    }

    @Test
    void testGetCustomerByIdFound() {
        UUID id = UUID.randomUUID();
        String email = "test@example.com";
        Customer customer = new Customer();
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(id, "", email, BigDecimal.valueOf(100), LocalDateTime.now(), "GOLD");
        when(customerRepository.findById(any(UUID.class))).thenReturn(Optional.of(customer));
        when(customerMapper.toResponse(customer)).thenReturn(responseDTO);

        CustomerResponseDTO result = customerService.getCustomerById(id);
        assertEquals(responseDTO, result);
    }

    @Test
    void testGetCustomerByIdNotFound() {
        UUID id = UUID.randomUUID();
        when(customerRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(Exception.class, () -> customerService.getCustomerById(id));
    }

    @Test
    void testFindCustomerByNameFound() {
        UUID id = UUID.randomUUID();
        String name = "test";
        String email = "test@example.com";
        Customer customer = new Customer();
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(id, name, email, BigDecimal.valueOf(100), LocalDateTime.now(), "GOLD");
        when(customerRepository.findCustomerByName(name)).thenReturn(Optional.of(customer));
        when(customerMapper.toResponse(customer)).thenReturn(responseDTO);
        CustomerResponseDTO result = customerService.findCustomerByName(name);
        assertEquals(responseDTO, result);
    }

    @Test
    void testFindCustomerByNameNotFound() {
        when(customerRepository.findCustomerByName(anyString())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> customerService.findCustomerByName("unknown"));
    }

    @Test
    void testCreateCustomer() {
        UUID id = UUID.randomUUID();
        CustomerRequestDTO requestDTO = new CustomerRequestDTO("John", "john@example.com", BigDecimal.valueOf(1000), LocalDateTime.now());
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(id, "john", "john@example.com", BigDecimal.valueOf(1000), LocalDateTime.now(), "GOLD");
        Customer customer = new Customer();
        Customer savedCustomer = new Customer();
        when(customerMapper.toEntity(requestDTO)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        when(customerMapper.toResponse(savedCustomer)).thenReturn(responseDTO);
        CustomerResponseDTO result = customerService.create(requestDTO);
        assertEquals(responseDTO, result);
    }

    @Test
    void testUpdateCustomerById() {
        UUID id = UUID.randomUUID();
        CustomerRequestDTO requestDTO = new CustomerRequestDTO("John", "john@example.com", BigDecimal.valueOf(1000), LocalDateTime.now());
        CustomerResponseDTO responseDTO = new CustomerResponseDTO(id, "john", "john@example.com", BigDecimal.valueOf(1000), LocalDateTime.now(), "GOLD");
        when(customerRepository.findById(id)).thenReturn(Optional.of(new Customer()));
        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer());
        when(customerMapper.toResponse(any(Customer.class))).thenReturn(responseDTO);

        CustomerResponseDTO result = customerService.updateCustomerById(id, requestDTO);
        assertEquals(responseDTO, result);
    }

    @Test
    void testDeleteCustomerById() {
        UUID id = UUID.randomUUID();
        Customer customer = new Customer();
        when(customerRepository.findById(id)).thenReturn(Optional.of(customer));
        customerService.deleteCustomerById(id);
        verify(customerRepository).delete(customer);
    }
}

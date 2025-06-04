
package com.codingevaluationcustomerapimgmt.controller;

import com.codingevaluationcustomerapimgmt.model.dtos.request.CustomerRequestDTO;
import com.codingevaluationcustomerapimgmt.model.dtos.response.CustomerResponseDTO;
import com.codingevaluationcustomerapimgmt.service.CustomerService;
import com.codingevaluationcustomerapimgmt.service.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CustomerServiceImpl customerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateCustomer() throws Exception {
        CustomerRequestDTO request = new CustomerRequestDTO("John Doe", "john@example.com", new BigDecimal("1000.00"), LocalDateTime.now());
        CustomerResponseDTO response = new CustomerResponseDTO(UUID.randomUUID(), "John Doe", "john@example.com",
                new BigDecimal("1000.00"), LocalDateTime.now(), "GOLD");
        Mockito.when(customerService.create(Mockito.any())).thenReturn(response);
        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    public void testGetCustomerById() throws Exception {
        UUID id = UUID.randomUUID();
        CustomerResponseDTO response = new CustomerResponseDTO(id, "Jane Doe", "jane@example.com", new BigDecimal("2000.00"),
                LocalDateTime.now(), "GOLD");

        Mockito.when(customerService.getCustomerById(id)).thenReturn(response);
        mockMvc.perform(get(String.format("/customers/%s", id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    @Test
    public void testGetCustomerByEmail() throws Exception {
        String email = "user@example.com";
        CustomerResponseDTO response = new CustomerResponseDTO(UUID.randomUUID(), "Email User", email, new BigDecimal("1200.00"), LocalDateTime.now(), "GOLD");

        Mockito.when(customerService.getCustomerByEmail(email)).thenReturn(response);

        mockMvc.perform(get("/customers").param("email", email))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value(email));
    }

    @Test
    public void testGetCustomerByName() throws Exception {
        String name = "Search Name";
        CustomerResponseDTO response = new CustomerResponseDTO(UUID.randomUUID(), name, "name@example.com", new BigDecimal("1500.00"), LocalDateTime.now(), "GOLD");
        Mockito.when(customerService.findCustomerByName(name)).thenReturn(response);

        mockMvc.perform(get("/customers").param("name", name))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        UUID id = UUID.randomUUID();
        CustomerRequestDTO request = new CustomerRequestDTO("Updated User", "updated@example.com", new BigDecimal("3000.00"), LocalDateTime.now());
        CustomerResponseDTO response = new CustomerResponseDTO(id, "Updated User", "updated@example.com", new BigDecimal("3000.00"), LocalDateTime.now(), "GOLD");

        Mockito.when(customerService.updateCustomerById(Mockito.eq(id), Mockito.any())).thenReturn(response);

        mockMvc.perform(put(String.format("/customers/%s", id))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated User"));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        UUID id = UUID.randomUUID();
        mockMvc.perform(delete(String.format("/customers/%s", id)))
                .andExpect(status().isNoContent());
        Mockito.verify(customerService).deleteCustomerById(id);
    }
}

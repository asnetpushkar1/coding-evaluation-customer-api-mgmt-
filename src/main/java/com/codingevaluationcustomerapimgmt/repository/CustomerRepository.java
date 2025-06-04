package com.codingevaluationcustomerapimgmt.repository;

import com.codingevaluationcustomerapimgmt.model.enttites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByEmail(String email);

    Optional<Customer>findCustomerByName(String name);
}

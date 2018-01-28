package com.edu.restmvc.repository;

import com.edu.restmvc.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByNameAndSurname(String name, String surname);
}

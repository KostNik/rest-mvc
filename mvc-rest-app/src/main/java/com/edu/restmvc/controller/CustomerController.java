package com.edu.restmvc.controller;

import com.edu.model.CustomerDTO;
import com.edu.model.CustomerListDTO;
import com.edu.restmvc.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by SweetHome on 28/01/2018
 */

@Controller
@RequestMapping("/api/customers")
public class CustomerController {

    public static final String CUSTOMER_BASE_URL = "/api/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {this.customerService = customerService;}

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers() {
        List<CustomerDTO> all = customerService.getAll();
        CustomerListDTO customerListDTO = new CustomerListDTO();
        customerListDTO.getCustomers().addAll(all);
        return ResponseEntity.ok(customerListDTO);
    }

    @GetMapping("/customer")
    public ResponseEntity<CustomerDTO> getCustomerByNameAndSurname(@RequestParam("firstname") String name, @RequestParam("lastname") String surname) {
        CustomerDTO customerDTO = customerService.getByNameAndSurname(name, surname);
        return ResponseEntity.ok(customerDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO customerDTO = customerService.getById(id);
        return ResponseEntity.ok(customerDTO);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> addNewCustomer(@RequestBody CustomerDTO customer) {
        CustomerDTO customerDTO = customerService.addNewCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customer, @PathVariable Long id) {
        CustomerDTO customerDTO = customerService.updateCustomer(id, customer);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomerDTO> patchCustomer(@RequestBody CustomerDTO customer, @PathVariable Long id) {
        CustomerDTO customerDTO = customerService.patchCustomer(id, customer);
        return ResponseEntity.status(HttpStatus.OK).body(customerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}

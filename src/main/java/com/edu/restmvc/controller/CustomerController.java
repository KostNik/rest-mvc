package com.edu.restmvc.controller;

import com.edu.restmvc.model.CustomerDTO;
import com.edu.restmvc.model.CustomerListDTO;
import com.edu.restmvc.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by SweetHome on 28/01/2018
 */

@Controller
@RequestMapping("/api/customers/")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {this.customerService = customerService;}

    @GetMapping
    public ResponseEntity<CustomerListDTO> getAllCustomers() {
        List<CustomerDTO> all = customerService.getAll();
        CustomerListDTO customerListDTO = new CustomerListDTO(all);
        return ResponseEntity.ok(customerListDTO);
    }

    @GetMapping("customer")
    public ResponseEntity<CustomerDTO> getCustomerByNameAndSurname(@RequestParam("firstname") String name, @RequestParam("lastname") String surname) {
        CustomerDTO customerDTO = customerService.getByNameAndSurname(name, surname);
        return ResponseEntity.ok(customerDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
        CustomerDTO customerDTO = customerService.getById(id);
        return ResponseEntity.ok(customerDTO);
    }


}

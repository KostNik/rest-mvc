package com.edu.restmvc.service;

import com.edu.restmvc.mapper.CustomerMapper;
import com.edu.restmvc.model.CustomerDTO;
import com.edu.restmvc.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kostiuk Nikita on 28/01/2018
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper     customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }


    @Override
    public CustomerDTO getByNameAndSurname(String name, String surname) {
        return customerRepository.findByNameAndSurname(name, surname).map(customerMapper::customerToCustomerDTO).orElse(null);
    }

    @Override
    public CustomerDTO getById(Long id) {
        return customerRepository.findById(id).map(customerMapper::customerToCustomerDTO).orElse(null);
    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }
}

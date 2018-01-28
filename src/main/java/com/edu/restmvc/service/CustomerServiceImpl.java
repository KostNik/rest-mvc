package com.edu.restmvc.service;

import com.edu.restmvc.domain.Customer;
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
        return customerRepository.findByFirstnameAndLastname(name, surname).map(customerMapper::customerToCustomerDTO).orElseThrow(RuntimeException::new);
    }

    @Override
    public CustomerDTO getById(Long id) {
        return customerRepository.findById(id).map(customerMapper::customerToCustomerDTO).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO addNewCustomer(CustomerDTO customer) {
        Customer customerToSave = customerMapper.customerDTOToCustomer(customer);
        Customer saved = customerRepository.save(customerToSave);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(saved);
        customerDTO.setCustomerUrl("/api/customers/" + customerDTO.getId());
        return customerDTO;
    }
}

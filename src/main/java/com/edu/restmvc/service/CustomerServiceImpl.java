package com.edu.restmvc.service;

import com.edu.restmvc.domain.Customer;
import com.edu.restmvc.mapper.CustomerMapper;
import com.edu.restmvc.model.CustomerDTO;
import com.edu.restmvc.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.edu.restmvc.controller.CustomerController.CUSTOMER_BASE_URL;

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
                .peek(dto -> dto.setCustomerUrl(CUSTOMER_BASE_URL + "/" + dto.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO addNewCustomer(CustomerDTO customer) {
        Customer customerToSave = customerMapper.customerDTOToCustomer(customer);
        return saveAndReturnDTO(customerToSave);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customer) {
        Customer dtoToCustomer = customerMapper.customerDTOToCustomer(customer);
        dtoToCustomer.setId(id);
        return saveAndReturnDTO(dtoToCustomer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id).map(customer -> {
            if (!StringUtils.isEmpty(customerDTO.getFirstname())) {
                customer.setFirstname(customerDTO.getFirstname());
            }
            if (!StringUtils.isEmpty(customerDTO.getLastname())) {
                customer.setLastname(customerDTO.getLastname());
            }
            return saveAndReturnDTO(customer);
        }).orElseThrow(RuntimeException::new);
    }

    private CustomerDTO saveAndReturnDTO(Customer customer) {
        Customer saved = customerRepository.save(customer);
        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(saved);
        customerDTO.setCustomerUrl(CUSTOMER_BASE_URL + "/" + customerDTO.getId());
        return customerDTO;
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}

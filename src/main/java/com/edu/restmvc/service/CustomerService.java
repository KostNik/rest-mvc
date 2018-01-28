package com.edu.restmvc.service;

import com.edu.restmvc.model.CustomerDTO;

import java.util.List;

/**
 * Created by SweetHome on 28 of January, 2018
 */
public interface CustomerService {

    CustomerDTO getByNameAndSurname(String name, String surname);

    CustomerDTO getById(Long id);

    List<CustomerDTO> getAll();

    CustomerDTO addNewCustomer(CustomerDTO customer);

    CustomerDTO updateCustomer(Long id, CustomerDTO customer);

    CustomerDTO patchCustomer(Long id, CustomerDTO customer);

    void deleteById(Long id);
}

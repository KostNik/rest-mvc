package com.edu.restmvc.service;

import com.edu.model.CustomerDTO;
import com.edu.restmvc.domain.Customer;
import com.edu.restmvc.mapper.CustomerMapper;
import com.edu.restmvc.repository.CustomerRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.edu.restmvc.controller.CustomerController.CUSTOMER_BASE_URL;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Kostiuk Nikita on 28/01/2018
 */
public class CustomerServiceTest {

    private final static Long   ID_1      = 3L;
    private final static String NAME_1    = "Bill";
    private final static String SURNAME_1 = "Yellow";

    private final static Long   ID_2      = 4L;
    private final static String NAME_2    = "Susy";
    private final static String SURNAME_2 = "Brown";

    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    public void testGetByNameAndSurname() {
        Customer customer = new Customer(NAME_1, SURNAME_1);
        customer.setId(ID_1);

        when(customerRepository.findByFirstnameAndLastname(anyString(), anyString())).thenReturn(Optional.of(customer));
        CustomerDTO customerDTO = customerService.getByNameAndSurname(NAME_1, SURNAME_1);
        assertEquals(ID_1, customerDTO.getId());
        assertEquals(NAME_1, customerDTO.getFirstname());
        assertEquals(SURNAME_1, customerDTO.getLastname());
    }

    @Test
    public void testGetById() {
        Customer customer = new Customer(NAME_1, SURNAME_1);
        customer.setId(ID_1);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        CustomerDTO customerDTO = customerService.getById(ID_1);
        assertEquals(ID_1, customerDTO.getId());
        assertEquals(NAME_1, customerDTO.getFirstname());
        assertEquals(SURNAME_1, customerDTO.getLastname());

    }

    @Test
    public void testGetAll() {
        Customer customer_1 = new Customer(NAME_1, SURNAME_1);
        customer_1.setId(ID_1);

        Customer customer_2 = new Customer(NAME_2, SURNAME_2);
        customer_2.setId(ID_2);

        when(customerRepository.findAll()).thenReturn(Lists.newArrayList(customer_1, customer_2));
        List<CustomerDTO> all = customerService.getAll();

        assertEquals(2, all.size());
    }


    @Test
    public void testAddNewCustomer() {
        Customer customer_1 = new Customer(NAME_1, SURNAME_1);
        customer_1.setId(ID_1);

        CustomerDTO customerDTO = getCustomerDTO(customer_1.getId(), customer_1.getFirstname(), customer_1.getLastname());
        String customerUrl = CUSTOMER_BASE_URL + "/" + ID_1;
        customerDTO.setCustomerUrl(customerUrl);

        when(customerRepository.save(any(Customer.class))).thenReturn(customer_1);

        CustomerDTO customerSaved = customerService.addNewCustomer(customerDTO);

        assertEquals(NAME_1, customerSaved.getFirstname());
        assertEquals(SURNAME_1, customerSaved.getLastname());
        assertEquals(customerUrl, customerSaved.getCustomerUrl());
    }


    @Test
    public void testUpdateCustomer() {
        Customer customer_1 = new Customer(NAME_1, SURNAME_1);
        customer_1.setId(ID_1);

        CustomerDTO customerDTO = getCustomerDTO(customer_1.getId(), customer_1.getFirstname(), customer_1.getLastname());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer_1);

        CustomerDTO customerSaved = customerService.updateCustomer(ID_1, customerDTO);

        verify(customerRepository, times(1)).save(any(Customer.class));

        assertEquals(customerDTO.getFirstname(), customerSaved.getFirstname());
        assertEquals(customerDTO.getLastname(), customerSaved.getLastname());


    }
    private CustomerDTO getCustomerDTO(Long id, String name, String surname) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(id);
        customerDTO.setFirstname(name);
        customerDTO.setLastname(surname);
        return customerDTO;
    }

    @Test
    public void testDeleteCustomer() {

        customerService.deleteById(ID_1);

        verify(customerRepository, times(1)).deleteById(anyLong());

    }

}
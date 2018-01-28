package com.edu.restmvc.service;

import com.edu.restmvc.domain.Customer;
import com.edu.restmvc.mapper.CustomerMapper;
import com.edu.restmvc.model.CustomerDTO;
import com.edu.restmvc.repository.CustomerRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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
    public void getByNameAndSurname() {
        Customer customer = new Customer(NAME_1, SURNAME_1);
        customer.setId(ID_1);

        when(customerRepository.findByNameAndSurname(anyString(), anyString())).thenReturn(Optional.of(customer));
        CustomerDTO customerDTO = customerService.getByNameAndSurname(NAME_1, SURNAME_1);
        assertEquals(ID_1, customerDTO.getId());
        assertEquals(NAME_1, customerDTO.getName());
        assertEquals(SURNAME_1, customerDTO.getSurname());
    }

    @Test
    public void getById() {
        Customer customer = new Customer(NAME_1, SURNAME_1);
        customer.setId(ID_1);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.of(customer));
        CustomerDTO customerDTO = customerService.getById(ID_1);
        assertEquals(ID_1, customerDTO.getId());
        assertEquals(NAME_1, customerDTO.getName());
        assertEquals(SURNAME_1, customerDTO.getSurname());

    }

    @Test
    public void getAll() {
        Customer customer_1 = new Customer(NAME_1, SURNAME_1);
        customer_1.setId(ID_1);

        Customer customer_2 = new Customer(NAME_2, SURNAME_2);
        customer_2.setId(ID_2);

        when(customerRepository.findAll()).thenReturn(Lists.newArrayList(customer_1, customer_2));
        List<CustomerDTO> all = customerService.getAll();

        assertEquals(2, all.size());
    }
}
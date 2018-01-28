package com.edu.restmvc.mapper;

import com.edu.restmvc.domain.Customer;
import com.edu.restmvc.model.CustomerDTO;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Id;

import static org.junit.Assert.*;

/**
 * Created by Kostiuk Nikita on 28/01/2018
 */
public class CustomerMapperTest {

    private final static Long   ID      = 3L;
    private final static String NAME    = "Bill";
    private final static String SURNAME = "Yellow";

    private CustomerMapper customerMapper;

    @Before
    public void setUp() throws Exception {
        customerMapper = CustomerMapper.INSTANCE;
    }

    @Test
    public void customerToCustomerDTO() {

        Customer customer = new Customer(NAME, SURNAME);
        customer.setId(ID);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertNotNull(customerDTO);
        assertEquals(ID, customerDTO.getId());
        assertEquals(NAME, customerDTO.getName());
        assertEquals(SURNAME, customerDTO.getSurname());

    }
}
package com.edu.restmvc.service;

import com.edu.restmvc.bootstrap.Bootstrap;
import com.edu.restmvc.domain.Customer;
import com.edu.restmvc.mapper.CustomerMapper;
import com.edu.restmvc.model.CustomerDTO;
import com.edu.restmvc.repository.CategoryRepository;
import com.edu.restmvc.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Created by Kostiuk Nikita on 28/01/2018
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerServiceIT {

    private final static String MODIFICATION = "Superman";


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private CustomerService customerService;


    @Before
    public void setUp() throws Exception {
        Bootstrap bootstrap = new Bootstrap(categoryRepository, customerRepository);
        bootstrap.run();

        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }


    @Test
    public void testPatchCustomerOnFirstname() {
        Long id = getFirstCustomerId();
        Customer customerBase = customerRepository.getOne(id);
        assertNotNull(customerBase);

        String baseFirstname = customerBase.getFirstname();
        String baseLastname = customerBase.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(MODIFICATION);

        customerService.patchCustomer(id, customerDTO);

        Customer patchedCustomer = customerRepository.getOne(id);

        assertNotNull(patchedCustomer);
        assertEquals(MODIFICATION, patchedCustomer.getFirstname());
        assertThat(baseFirstname, not(equalTo(patchedCustomer.getFirstname())));
        assertEquals(baseLastname, patchedCustomer.getLastname());

    }


    @Test
    public void testPatchCustomerOnLastname() {
        Long id = getFirstCustomerId();
        Customer customerBase = customerRepository.getOne(id);
        assertNotNull(customerBase);

        String baseFirstname = customerBase.getFirstname();
        String baseLastname = customerBase.getLastname();

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname(MODIFICATION);

        customerService.patchCustomer(id, customerDTO);

        Customer patchedCustomer = customerRepository.getOne(id);

        assertNotNull(patchedCustomer);
        assertEquals(MODIFICATION, patchedCustomer.getLastname());
        assertThat(baseFirstname, equalTo(patchedCustomer.getFirstname()));
        assertNotEquals(baseLastname, patchedCustomer.getLastname());

    }


    private Long getFirstCustomerId() {
        return customerRepository.findAll().get(0).getId();
    }


}

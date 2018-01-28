package com.edu.restmvc.controller;

import com.edu.restmvc.model.CustomerDTO;
import com.edu.restmvc.service.CustomerService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Kostiuk Nikita on 28/01/2018
 */
public class CustomerControllerTest {


    private final static Long   ID_1      = 3L;
    private final static String NAME_1    = "Bill";
    private final static String SURNAME_1 = "Yellow";

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void getAllCustomers() throws Exception {

        when(customerService.getAll()).thenReturn(Lists.newArrayList(new CustomerDTO(), new CustomerDTO(), new CustomerDTO()));

        mockMvc.perform(get("/api/customers/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(3)));

    }

    @Test
    public void getCustomerByNameAndSurname() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO(ID_1, NAME_1, SURNAME_1);
        when(customerService.getByNameAndSurname(anyString(), anyString())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/customers/customer?name=anyName&surname=anySurname")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_1)))
                .andExpect(jsonPath("$.surname", equalTo(SURNAME_1)));
//                .andExpect(jsonPath("$.id", equalTo(ID_1)));

    }

    @Test
    public void getCustomerById() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO(ID_1, NAME_1, SURNAME_1);
        when(customerService.getById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get("/api/customers/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_1)))
                .andExpect(jsonPath("$.surname", equalTo(SURNAME_1)));
//                .andExpect(jsonPath("$.id", equalTo(ID_1)));

    }
}
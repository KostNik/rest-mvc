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

import static com.edu.restmvc.controller.CustomerController.CUSTOMER_BASE_URL;
import static com.edu.restmvc.utils.Utils.fromObjectToJSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testGetAllCustomers() throws Exception {

        when(customerService.getAll()).thenReturn(Lists.newArrayList(new CustomerDTO(), new CustomerDTO(), new CustomerDTO()));

        mockMvc.perform(get(CUSTOMER_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(3)));

    }

    @Test
    public void testGetCustomerByNameAndSurname() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO(ID_1, NAME_1, SURNAME_1);
        when(customerService.getByNameAndSurname(anyString(), anyString())).thenReturn(customerDTO);

        mockMvc.perform(get(CUSTOMER_BASE_URL + "/customer?firstname=anyName&lastname=anySurname")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(NAME_1)))
                .andExpect(jsonPath("$.lastname", equalTo(SURNAME_1)));

    }

    @Test
    public void testGetCustomerById() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO(ID_1, NAME_1, SURNAME_1);
        when(customerService.getById(anyLong())).thenReturn(customerDTO);

        mockMvc.perform(get(CUSTOMER_BASE_URL + "/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(NAME_1)))
                .andExpect(jsonPath("$.lastname", equalTo(SURNAME_1)));

    }

    @Test
    public void testAddNewCustomer() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO(ID_1, NAME_1, SURNAME_1);

        CustomerDTO customerDTOSaved = new CustomerDTO(ID_1, NAME_1, SURNAME_1);
        String customerUrl = CUSTOMER_BASE_URL + "/" + ID_1;
        customerDTOSaved.setCustomerUrl(customerUrl);

        when(customerService.addNewCustomer(customerDTO)).thenReturn(customerDTOSaved);

        mockMvc.perform(post(CUSTOMER_BASE_URL)
                .content(fromObjectToJSON(customerDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstname", equalTo(NAME_1)))
                .andExpect(jsonPath("$.customer_url", equalTo(customerUrl)))
                .andExpect(jsonPath("$.lastname", equalTo(SURNAME_1)));
    }

    @Test
    public void testUpdateCustomer() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO(NAME_1, SURNAME_1);

        CustomerDTO customerDTOReturned = new CustomerDTO(ID_1, NAME_1, SURNAME_1);
        String customerUrl = CUSTOMER_BASE_URL + "/" + ID_1;
        customerDTOReturned.setCustomerUrl(customerUrl);

        when(customerService.updateCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(customerDTOReturned);

        mockMvc.perform(put(CUSTOMER_BASE_URL + "/" + ID_1)
                .content(fromObjectToJSON(customerDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(NAME_1)))
                .andExpect(jsonPath("$.customer_url", equalTo(customerUrl)))
                .andExpect(jsonPath("$.lastname", equalTo(SURNAME_1)));
    }

    @Test
    public void testPatchCustomer() throws Exception {

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname(NAME_1);

        CustomerDTO customerDTOReturned = new CustomerDTO(ID_1, NAME_1, SURNAME_1);
        String customerUrl = CUSTOMER_BASE_URL + "/" + ID_1;
        customerDTOReturned.setCustomerUrl(customerUrl);

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(customerDTOReturned);

        mockMvc.perform(patch(CUSTOMER_BASE_URL + "/" + ID_1)
                .content(fromObjectToJSON(customerDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstname", equalTo(NAME_1)))
                .andExpect(jsonPath("$.customer_url", equalTo(customerUrl)))
                .andExpect(jsonPath("$.lastname", equalTo(SURNAME_1)));
    }

    @Test
    public void testDeleteCustomer() throws Exception {

        mockMvc.perform(delete(CUSTOMER_BASE_URL + "/" + ID_1))
                .andExpect(status().isOk());

        verify(customerService, times(1)).deleteById(anyLong());

    }
}
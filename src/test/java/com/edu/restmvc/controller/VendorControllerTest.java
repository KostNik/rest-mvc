package com.edu.restmvc.controller;

import com.edu.restmvc.exceptions.ResourceNotFoundException;
import com.edu.restmvc.model.VendorDTO;
import com.edu.restmvc.service.VendorService;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.edu.restmvc.service.VendorServiceImpl.VENDOR_BASE_URL;
import static com.edu.restmvc.utils.Utils.fromObjectToJSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Kostiuk Nikita on 03/02/2018
 */
public class VendorControllerTest {

    private final static Long   ID_1   = 3L;
    private final static String NAME_1 = "Bill";

    @Mock
    private VendorService vendorService;

    @InjectMocks
    private VendorController vendorController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    public void testGetAllVendors() throws Exception {

        when(vendorService.getAll()).thenReturn(Lists.newArrayList(new VendorDTO(), new VendorDTO(), new VendorDTO()));

        mockMvc.perform(get(VENDOR_BASE_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vendors", hasSize(3)));

    }

    @Test
    public void testGetVendorByName() throws Exception {

        VendorDTO vendorDTO = new VendorDTO(ID_1, NAME_1);
        when(vendorService.getByName(anyString())).thenReturn(vendorDTO);

        mockMvc.perform(get(VENDOR_BASE_URL + "/vendor?name=anyName")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_1)));

    }

    @Test
    public void testGetVendorById() throws Exception {

        VendorDTO vendorDTO = new VendorDTO(ID_1, NAME_1);
        when(vendorService.getById(anyLong())).thenReturn(vendorDTO);

        mockMvc.perform(get(VENDOR_BASE_URL + "/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_1)));

    }

    @Test
    public void testAddNewVendor() throws Exception {

        VendorDTO vendorDTO = new VendorDTO(ID_1, NAME_1);

        VendorDTO vendorDTOSaved = new VendorDTO(ID_1, NAME_1);
        String vendorUrl = VENDOR_BASE_URL + "/" + ID_1;
        vendorDTOSaved.setVendorUrl(vendorUrl);

        when(vendorService.addNewVendor(vendorDTO)).thenReturn(vendorDTOSaved);

        mockMvc.perform(post(VENDOR_BASE_URL)
                .content(fromObjectToJSON(vendorDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", equalTo(NAME_1)))
                .andExpect(jsonPath("$.vendor_url", equalTo(vendorUrl)));
    }

    @Test
    public void testUpdateVendor() throws Exception {

        VendorDTO vendorDTO = new VendorDTO(NAME_1);

        VendorDTO vendorDTOReturned = new VendorDTO(ID_1, NAME_1);
        String vendorUrl = VENDOR_BASE_URL + "/" + ID_1;
        vendorDTOReturned.setVendorUrl(vendorUrl);

        when(vendorService.updateVendor(anyLong(), any(VendorDTO.class))).thenReturn(vendorDTOReturned);

        mockMvc.perform(put(VENDOR_BASE_URL + "/" + ID_1)
                .content(fromObjectToJSON(vendorDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_1)))
                .andExpect(jsonPath("$.vendor_url", equalTo(vendorUrl)));
    }

    @Test
    public void testPatchVendor() throws Exception {

        VendorDTO vendorDTO = new VendorDTO(NAME_1);

        VendorDTO vendorDTOReturned = new VendorDTO(ID_1, NAME_1);
        String vendorUrl = VENDOR_BASE_URL + "/" + ID_1;
        vendorDTOReturned.setVendorUrl(vendorUrl);

        when(vendorService.patchVendor(anyLong(), any(VendorDTO.class))).thenReturn(vendorDTOReturned);

        mockMvc.perform(patch(VENDOR_BASE_URL + "/" + ID_1)
                .content(fromObjectToJSON(vendorDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_1)))
                .andExpect(jsonPath("$.vendor_url", equalTo(vendorUrl)));
    }

    @Test
    public void testDeleteVendor() throws Exception {
        mockMvc.perform(delete(VENDOR_BASE_URL + "/" + ID_1))
                .andExpect(status().isOk());
        verify(vendorService, times(1)).deleteById(anyLong());

    }

    @Test
    public void notFoundStatusTest() throws Exception {

        when(vendorService.getById(anyLong())).thenThrow(ResourceNotFoundException.class);
        mockMvc.perform(get(VENDOR_BASE_URL + "/" + ID_1))
                .andExpect(status().isNotFound());

    }
}
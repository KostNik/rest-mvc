package com.edu.restmvc.service;

import com.edu.restmvc.domain.Vendor;
import com.edu.restmvc.exceptions.ResourceNotFoundException;
import com.edu.restmvc.mapper.VendorMapper;
import com.edu.restmvc.model.VendorDTO;
import com.edu.restmvc.repository.VendorRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.edu.restmvc.service.VendorServiceImpl.VENDOR_BASE_URL;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

/**
 * Created by Kostiuk Nikita on 28/01/2018
 */
public class VendorServiceTest {

    private final static Long   ID_1   = 3L;
    private final static String NAME_1 = "Bill";

    private final static Long   ID_2   = 4L;
    private final static String NAME_2 = "Susy";

    @Mock
    private VendorRepository vendorRepository;

    private VendorService vendorService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vendorService = new VendorServiceImpl(vendorRepository, VendorMapper.INSTANCE);
    }

    @Test
    public void testGetByName() {
        Vendor vendor = new Vendor(NAME_1);
        vendor.setId(ID_1);

        when(vendorRepository.findByName(anyString())).thenReturn(Optional.of(vendor));
        VendorDTO vendorDTO = vendorService.getByName(NAME_1);
        assertEquals(ID_1, vendorDTO.getId());
        assertEquals(NAME_1, vendorDTO.getName());
    }

    @Test
    public void testGetById() {
        Vendor vendor = new Vendor(NAME_1);
        vendor.setId(ID_1);


        given(vendorRepository.findById(anyLong())).willReturn(Optional.of(vendor));

        VendorDTO vendorDTO = vendorService.getById(ID_1);


        then(vendorRepository).should(times(1)).findById(anyLong());
        assertEquals(ID_1, vendorDTO.getId());
        assertEquals(NAME_1, vendorDTO.getName());

    }

    @Test
    public void testGetAll() {
        Vendor vendor_1 = new Vendor(NAME_1);
        vendor_1.setId(ID_1);

        Vendor vendor_2 = new Vendor(NAME_2);
        vendor_2.setId(ID_2);

        when(vendorRepository.findAll()).thenReturn(Lists.newArrayList(vendor_1, vendor_2));
        List<VendorDTO> all = vendorService.getAll();

        assertEquals(2, all.size());
    }


    @Test
    public void testAddNewVendor() {
        Vendor vendor_1 = new Vendor(NAME_1);
        vendor_1.setId(ID_1);

        String vendorUrl = VENDOR_BASE_URL + "/" + ID_1;
        VendorDTO vendorDTO = new VendorDTO(vendor_1.getId(), vendor_1.getName(), vendorUrl);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor_1);

        VendorDTO vendorSaved = vendorService.addNewVendor(vendorDTO);

        assertEquals(NAME_1, vendorSaved.getName());
        assertEquals(vendorUrl, vendorSaved.getVendorUrl());
    }


    @Test
    public void testUpdateVendor() {
        Vendor vendor_1 = new Vendor(NAME_1);
        vendor_1.setId(ID_1);

        String vendorUrl = VENDOR_BASE_URL + "/" + ID_1;
        VendorDTO vendorDTO = new VendorDTO(vendor_1.getId(), vendor_1.getName(), vendorUrl);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(vendor_1);

        VendorDTO vendorSaved = vendorService.updateVendor(ID_1, vendorDTO);

        assertEquals(NAME_1, vendorSaved.getName());
        assertEquals(vendorUrl, vendorSaved.getVendorUrl());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testResourceNotFound() {
        when(vendorRepository.findById(anyLong())).thenReturn(Optional.empty());
        vendorService.getById(anyLong());
    }

    @Test
    public void testDeleteVendor() {
        vendorService.deleteById(ID_1);
        verify(vendorRepository, times(1)).deleteById(anyLong());
    }

}
package com.edu.restmvc.mapper;

import com.edu.restmvc.domain.Vendor;
import com.edu.restmvc.domain.Vendor;
import com.edu.restmvc.model.VendorDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class VendorMapperTest {

    private static final Long   ID   = 3L;
    private static final String NAME = "John";

    private VendorMapper vendorMapper;

    @Before
    public void setUp() throws Exception {
        vendorMapper = VendorMapper.INSTANCE;
    }

    @Test
    public void testVendorToVendorDTO() {
        Vendor vendor = new Vendor();
        vendor.setId(ID);
        vendor.setName(NAME);

        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);

        assertNotNull(vendorDTO);
        assertEquals(ID, vendorDTO.getId());
        assertEquals(NAME, vendorDTO.getName());

    }

    @Test
    public void testVendorDTOToVendor() {
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setId(ID);
        vendorDTO.setName(NAME);

        Vendor vendor = vendorMapper.vendorDTOToVendor(vendorDTO);

        assertNotNull(vendor);
        assertEquals(ID, vendor.getId());
        assertEquals(NAME, vendor.getName());

    }
}
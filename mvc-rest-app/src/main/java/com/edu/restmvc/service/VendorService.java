package com.edu.restmvc.service;

import com.edu.restmvc.model.VendorDTO;

import java.util.List;

/**
 * Created by Kostiuk Nikita
 */
public interface VendorService {


    VendorDTO getById(Long id);

    VendorDTO getByName(String name);

    List<VendorDTO> getAll();

    void deleteById(Long id);

    VendorDTO addNewVendor(VendorDTO vendor);

    VendorDTO updateVendor(Long id, VendorDTO vendor);

    VendorDTO patchVendor(Long id, VendorDTO vendor);

}

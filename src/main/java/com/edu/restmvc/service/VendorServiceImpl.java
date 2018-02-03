package com.edu.restmvc.service;

import com.edu.restmvc.domain.Vendor;
import com.edu.restmvc.exceptions.ResourceNotFoundException;
import com.edu.restmvc.mapper.VendorMapper;
import com.edu.restmvc.model.VendorDTO;
import com.edu.restmvc.repository.VendorRepository;
import com.google.common.collect.Streams;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Kostiuk Nikita
 */

@Service
public class VendorServiceImpl implements VendorService {

    public static final String VENDOR_BASE_URL = "/api/vendors";

    private final VendorRepository vendorRepository;

    private final VendorMapper vendorMapper;

    public VendorServiceImpl(VendorRepository vendorRepository, VendorMapper vendorMapper) {
        this.vendorRepository = vendorRepository;
        this.vendorMapper = vendorMapper;
    }


    @Override
    public VendorDTO getById(Long id) {
        return vendorRepository.findById(id)
                .map(vendorMapper::vendorToVendorDTO)
                .map(v -> {
                    v.setVendorUrl(VENDOR_BASE_URL + "/" + v.getId());
                    return v;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorDTO getByName(String name) {
        return vendorRepository.findByName(name)
                .map(vendorMapper::vendorToVendorDTO)
                .map(v -> {
                    v.setVendorUrl(VENDOR_BASE_URL + "/" + v.getId());
                    return v;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<VendorDTO> getAll() {
        return Streams.stream(vendorRepository.findAll())
                .map(vendorMapper::vendorToVendorDTO)
                .peek(v -> v.setVendorUrl(VENDOR_BASE_URL + "/" + v.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        vendorRepository.deleteById(id);
    }

    @Override
    public VendorDTO addNewVendor(VendorDTO vendor) {
        Vendor vendorToSave = vendorMapper.vendorDTOToVendor(vendor);
        return saveAndReturnDTO(vendorToSave);
    }

    @Override
    public VendorDTO updateVendor(Long id, VendorDTO vendor) {
        Vendor vendorToSave = vendorMapper.vendorDTOToVendor(vendor);
        vendorToSave.setId(id);
        return saveAndReturnDTO(vendorToSave);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendor) {
        return vendorRepository.findById(id).map(v -> {
            if (!StringUtils.isEmpty(v.getName())) {
                vendor.setName(v.getName());
            }
            return saveAndReturnDTO(v);
        }).orElseThrow(ResourceNotFoundException::new);
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor saved = vendorRepository.save(vendor);
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(saved);
        vendorDTO.setVendorUrl(VENDOR_BASE_URL + "/" + vendor.getId());
        return vendorDTO;
    }
}

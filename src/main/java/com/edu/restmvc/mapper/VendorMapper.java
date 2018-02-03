package com.edu.restmvc.mapper;

import com.edu.restmvc.domain.Vendor;
import com.edu.restmvc.model.VendorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * Created by Kostiuk Nikita
 */

@Mapper
public interface VendorMapper {

    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    VendorDTO vendorToVendorDTO(Vendor vendor);

    Vendor vendorDTOToVendor(VendorDTO vendor);
}

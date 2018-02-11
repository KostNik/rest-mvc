package com.edu.restmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Kostiuk Nikita
 */
@Data
@AllArgsConstructor
public class VendorListDTO {

    private List<VendorDTO> vendors;

}

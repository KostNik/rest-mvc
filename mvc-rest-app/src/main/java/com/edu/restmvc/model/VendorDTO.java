package com.edu.restmvc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Kostiuk Nikita
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendorDTO {

    private Long id;

    @ApiModelProperty(name = "This is the name of Vendor", required = true)
    private String name;

    @JsonProperty("vendor_url")
    private String vendorUrl;

    public VendorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public VendorDTO(String name) {
        this.name = name;
    }
}

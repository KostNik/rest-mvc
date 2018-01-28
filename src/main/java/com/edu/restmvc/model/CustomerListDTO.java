package com.edu.restmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by Kostiuk Nikita on 28/01/2018
 */

@Data
@AllArgsConstructor
public class CustomerListDTO {

    private List<CustomerDTO> customers;

}



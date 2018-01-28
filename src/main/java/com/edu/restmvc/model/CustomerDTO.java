package com.edu.restmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Kostiuk Nikita on 28/01/2018
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    private Long   id;
    private String name;
    private String surname;

    public CustomerDTO(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}



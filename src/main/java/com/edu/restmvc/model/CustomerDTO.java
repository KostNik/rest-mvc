package com.edu.restmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Kostiuk Nikita on 28/01/2018
 */

@Data
@NoArgsConstructor
public class CustomerDTO {

    private Long   id;
    private String firstname;
    private String lastname;
    private String customerUrl;

    public CustomerDTO(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public CustomerDTO(Long id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}



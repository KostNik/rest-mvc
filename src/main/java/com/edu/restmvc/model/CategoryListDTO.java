package com.edu.restmvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * Created by SweetHome on 28/01/2018
 */
@Data
@AllArgsConstructor
public class CategoryListDTO {

    private List<CategoryDTO> categories;

}

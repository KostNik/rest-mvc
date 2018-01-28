package com.edu.restmvc.service;

import com.edu.restmvc.model.CategoryDTO;

import java.util.List;

/**
 * Created by SweetHome on 28 of January, 2018
 */
public interface CategoryService {

    CategoryDTO getByName(String name);

    List<CategoryDTO> getAll();
}

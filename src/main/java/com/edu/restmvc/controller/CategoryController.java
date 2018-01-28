package com.edu.restmvc.controller;

import com.edu.restmvc.model.CategoryDTO;
import com.edu.restmvc.model.CategoryListDTO;
import com.edu.restmvc.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by SweetHome on 28/01/2018
 */

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {this.categoryService = categoryService;}

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {
        List<CategoryDTO> all = categoryService.getAll();
        return new CategoryListDTO(all);
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable("name") String categoryName) {
        return categoryService.getByName(categoryName);
    }


}

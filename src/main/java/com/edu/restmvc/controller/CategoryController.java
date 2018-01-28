package com.edu.restmvc.controller;

import com.edu.restmvc.model.CategoryDTO;
import com.edu.restmvc.model.CategoryListDTO;
import com.edu.restmvc.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by SweetHome on 28/01/2018
 */

@Controller
@RequestMapping("/api/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {this.categoryService = categoryService;}

    @GetMapping
    public ResponseEntity<CategoryListDTO> getAllCategories() {
        List<CategoryDTO> all = categoryService.getAll();
        CategoryListDTO categoryListDTO = new CategoryListDTO(all);
//        return ResponseEntity.status(HttpStatus.OK).body(categoryListDTO);
        return ResponseEntity.ok(categoryListDTO);
    }

    @GetMapping("{name}")
    public ResponseEntity<CategoryDTO> getAllCategoryByName(@PathVariable("name") String categoryName) {
        CategoryDTO categoryDTO = categoryService.getByName(categoryName);
        return ResponseEntity.ok(categoryDTO);
    }



}

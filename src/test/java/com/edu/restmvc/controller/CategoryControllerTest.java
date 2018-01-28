package com.edu.restmvc.controller;

import com.edu.restmvc.domain.Category;
import com.edu.restmvc.mapper.CategoryMapper;
import com.edu.restmvc.model.CategoryDTO;
import com.edu.restmvc.service.CategoryService;
import com.edu.restmvc.service.CategoryServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by SweetHome on 28/01/2018
 */
public class CategoryControllerTest {


    private static final Long   ID_1   = 3L;
    private static final String NAME_1 = "John";

    private static final Long   ID_2   = 4L;
    private static final String NAME_2 = "Jenny";


    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryRepository;

    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryRepository).build();
    }


    @Test
    public void testGetAllCategories() throws Exception {
        CategoryDTO category_1 = new CategoryDTO();
        category_1.setId(ID_1);
        category_1.setName(NAME_1);

        CategoryDTO category_2 = new CategoryDTO();
        category_2.setId(ID_2);
        category_2.setName(NAME_2);

        when(categoryService.getAll()).thenReturn(Lists.newArrayList(category_1, category_2));

        mockMvc.perform(get("/api/categories")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categories", hasSize(2)));
    }


    @Test
    public void testByNameCategory() throws Exception {
        CategoryDTO category_1 = new CategoryDTO();
        category_1.setId(ID_1);
        category_1.setName(NAME_1);

        when(categoryService.getByName(anyString())).thenReturn(category_1);

        mockMvc.perform(get("/api/categories/anyString")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo(NAME_1)));
    }

}

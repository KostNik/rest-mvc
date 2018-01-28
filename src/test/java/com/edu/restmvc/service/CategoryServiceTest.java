package com.edu.restmvc.service;

import com.edu.restmvc.domain.Category;
import com.edu.restmvc.mapper.CategoryMapper;
import com.edu.restmvc.model.CategoryDTO;
import com.edu.restmvc.repository.CategoryRepository;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by SweetHome on 28/01/2018
 */
public class CategoryServiceTest {

    private static final Long   ID   = 3L;
    private static final String NAME = "John";

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    public void testGetByName() {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(category));

        CategoryDTO categoryDTO = categoryService.getByName(NAME);

        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());

    }

    @Test
    public void testGetAll() {

        when(categoryRepository.findAll()).thenReturn(Lists.newArrayList(new Category(), new Category(), new Category()));

        List<CategoryDTO> all = categoryService.getAll();
        assertEquals(3, all.size());
    }


}
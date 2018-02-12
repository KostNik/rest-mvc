package com.edu.restmvc.mapper;

import com.edu.restmvc.domain.Category;
import com.edu.restmvc.model.CategoryDTO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperTest {

    private static final Long   ID   = 3L;
    private static final String NAME = "John";

    private CategoryMapper categoryMapper;

    @Before
    public void setUp() throws Exception {
        categoryMapper = CategoryMapper.INSTANCE;
    }

    @Test
    public void testCategoryToCategoryDTO() {
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        assertNotNull(categoryDTO);
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());

    }
}
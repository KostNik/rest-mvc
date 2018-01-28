package com.edu.restmvc.bootstrap;

import com.edu.restmvc.domain.Category;
import com.edu.restmvc.repository.CategoryRepository;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("ARGS {}", Arrays.toString(args));

        Category fruits = new Category("Fruits");
        Category fresh = new Category("Fresh");
        Category exotic = new Category("Exotic");
        Category nuts = new Category("Nuts");
        Category dried = new Category("Dried");


        categoryRepository.saveAll(ImmutableSet.of(fruits, fresh, exotic, nuts, dried));

        log.info("Data size: {}", categoryRepository.count());
    }


}

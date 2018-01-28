package com.edu.restmvc.bootstrap;

import com.edu.restmvc.domain.Category;
import com.edu.restmvc.domain.Customer;
import com.edu.restmvc.repository.CategoryRepository;
import com.edu.restmvc.repository.CustomerRepository;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Category fruits = new Category("Fruits");
        Category fresh = new Category("Fresh");
        Category exotic = new Category("Exotic");
        Category nuts = new Category("Nuts");
        Category dried = new Category("Dried");


        categoryRepository.saveAll(ImmutableSet.of(fruits, fresh, exotic, nuts, dried));

        log.info("Category Data size: {}", categoryRepository.count());

        Customer customer_1 = new Customer("Ira", "La");
        Customer customer_2 = new Customer("Michael", "Lachappele");
        Customer customer_3 = new Customer("David", "Winter");
        Customer customer_4 = new Customer("Anne", "Hine");
        Customer customer_5 = new Customer("Alice", "Eastman");
        Customer customer_6 = new Customer("Fred", "Meyers");

        customerRepository.saveAll(ImmutableSet.of(customer_1, customer_2, customer_3, customer_4, customer_5, customer_6));

        log.info("Customer Data size: {}", categoryRepository.count());
    }


}

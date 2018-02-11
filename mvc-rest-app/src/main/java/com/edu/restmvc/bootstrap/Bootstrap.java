package com.edu.restmvc.bootstrap;

import com.edu.restmvc.domain.Category;
import com.edu.restmvc.domain.Customer;
import com.edu.restmvc.domain.Vendor;
import com.edu.restmvc.repository.CategoryRepository;
import com.edu.restmvc.repository.CustomerRepository;
import com.edu.restmvc.repository.VendorRepository;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private CategoryRepository categoryRepository;
    private CustomerRepository customerRepository;
    private VendorRepository   vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
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

        log.info("Customer Data size: {}", customerRepository.count());


        Vendor vendor_1 = new Vendor("PEPSI");
        Vendor vendor_2 = new Vendor("Coca-cola");
        Vendor vendor_3 = new Vendor("BMW");
        Vendor vendor_4 = new Vendor("AUDI");

        vendorRepository.saveAll(ImmutableSet.of(vendor_1, vendor_2, vendor_3, vendor_4));

        log.info("Vendor Data size: {}", vendorRepository.count());
    }


}

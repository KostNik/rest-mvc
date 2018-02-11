package com.edu.restmvc.repository;

import com.edu.restmvc.domain.Vendor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * Created by Kostiuk Nikita
 */
public interface VendorRepository extends CrudRepository<Vendor, Long> {

    Optional<Vendor> findByName(String name);
}

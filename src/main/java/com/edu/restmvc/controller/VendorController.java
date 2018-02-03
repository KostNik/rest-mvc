package com.edu.restmvc.controller;

import com.edu.restmvc.model.VendorDTO;
import com.edu.restmvc.model.VendorListDTO;
import com.edu.restmvc.service.VendorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Kostiuk Nikita
 */

@RestController
@RequestMapping("api/vendors")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/{id}")
    public VendorDTO getById(@PathVariable Long id) {
        return vendorService.getById(id);
    }

    @GetMapping("/vendor")
    public VendorDTO getByName(@RequestParam String name) {
        return vendorService.getByName(name);
    }


    @GetMapping
    public VendorListDTO getAll() {
        return new VendorListDTO(vendorService.getAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO addNewVendor(@RequestBody VendorDTO vendorDTO) {
        return vendorService.addNewVendor(vendorDTO);
    }

    @PutMapping("/{id}")
    public VendorDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.updateVendor(id, vendorDTO);
    }

    @PatchMapping("/{id}")
    public VendorDTO patchVendor(@PathVariable Long id, @RequestBody VendorDTO vendorDTO) {
        return vendorService.patchVendor(id, vendorDTO);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        vendorService.deleteById(id);
    }

}

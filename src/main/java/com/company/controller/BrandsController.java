package com.company.controller;

import com.company.entity.Brands;
import com.company.services.BrandsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/brands")
@AllArgsConstructor
public class BrandsController {

    BrandsService brandsService;

    @GetMapping
    public ResponseEntity<List<Brands>> getAllBrand() {
        return ResponseEntity.ok().body(brandsService.getAllBrands());
    }

    @PostMapping
    public ResponseEntity<Brands> save(@RequestBody Brands brand) {
        return ResponseEntity.ok().body(brandsService.save(brand));
    }

    @GetMapping("{id}")
    public ResponseEntity<Brands> findBrandById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(brandsService.findById(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<Brands> updateBrand(@RequestBody Brands brand, @PathVariable("id") Long id) {
        return ResponseEntity.ok().body(brandsService.update(brand, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        brandsService.delete(id);
        return ResponseEntity.ok().body("The brand has been deleted");
    }
}

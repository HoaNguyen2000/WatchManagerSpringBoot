package com.company.controller;

import com.company.entity.Brands;
import com.company.services.BrandsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/brands")
@AllArgsConstructor
public class BrandsController {

    BrandsService brandsService;

    @GetMapping
    public ResponseEntity<Page<Brands>> getAllBrand(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok().body(brandsService.getAllBrands(paging));
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

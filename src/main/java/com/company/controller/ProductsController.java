package com.company.controller;

import com.company.common.constants.Constant;
import com.company.dto.ResponeJson;
import com.company.entity.Product;
import com.company.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductsController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "3") int size){
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok().body(productService.findAll(paging));
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product){
        return ResponseEntity.ok(productService.save(product));
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> update(@RequestBody Product product,
                                          @PathVariable("id") Long id){
        return ResponseEntity.ok(productService.update(product, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponeJson<?>> deleteById(@PathVariable Long id){
        productService.delete(id);
        return ResponseEntity.ok(new ResponeJson<>(HttpStatus.OK, Constant.SUCCESS));
    }
}

package com.company.controller;

import com.company.common.constants.Constant;
import com.company.dto.ResponeJson;
import com.company.entity.Product;
import com.company.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductsController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        return ResponseEntity.ok().body(productService.findAll());
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

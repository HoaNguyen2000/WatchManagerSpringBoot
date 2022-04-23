package com.company.controller;

import com.company.common.constants.Constant;
import com.company.dto.ProductsDTO;
import com.company.dto.ResponeJson;
import com.company.entity.Product;
import com.company.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
public class ProductsController {

    private final ProductService productService;


    @GetMapping
    public ResponseEntity<Page<Product>> findAll(@RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "3") int size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok().body(productService.findAll(paging));
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> save(
            @RequestPart("image") MultipartFile image,
            @RequestPart("product") ProductsDTO product) {

        return ResponseEntity.ok(productService.save(product, image));

    }

    @PutMapping(path = "{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> update(
            @RequestPart("image") MultipartFile image,
            @RequestPart Product product,
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.update(product, image, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponeJson<?>> deleteById(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(new ResponeJson<>(HttpStatus.OK, Constant.SUCCESS));
    }
}

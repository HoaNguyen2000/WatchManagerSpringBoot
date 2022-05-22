package com.company.controller;

import com.company.common.constants.Constant;
import com.company.dto.ProductsDTO;
import com.company.dto.ResponeJson;
import com.company.entity.Brands;
import com.company.entity.Product;
import com.company.services.BrandsService;
import com.company.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "http://localhost:3006")
@RestController
@RequestMapping("api/v1/products")
@AllArgsConstructor
@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN"})
public class ProductsController {

    private final ProductService productService;
    private final BrandsService brandsService;


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
    public ResponseEntity<Product> save(@ModelAttribute ProductsDTO product) {
        Brands brands = brandsService.findById(product.getBrandId());
        String imageLink = productService.uploadImageWithIMGBB(product.getImage());
        return ResponseEntity.ok(productService.save(
                new Product(
                        product.getName(),
                        brands,
                        product.getSlug(),
                        product.getOldPrice(),
                        product.getPrice(),
                        product.getType(),
                        product.getDescription(),
                        imageLink)));
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Product> update(
            @ModelAttribute("product") ProductsDTO product,
            @PathVariable("id") Long id
    ) {
        Brands brands = brandsService.findById(product.getBrandId());
        String imageLink = productService.uploadImageWithIMGBB(product.getImage());
        return ResponseEntity.ok(productService.update(new Product(
                product.getName(),
                brands,
                product.getSlug(),
                product.getOldPrice(),
                product.getPrice(),
                product.getType(),
                product.getDescription(),
                imageLink),
                id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponeJson<?>> deleteById(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(new ResponeJson<>(HttpStatus.OK, Constant.SUCCESS));
    }
}

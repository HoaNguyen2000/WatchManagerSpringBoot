package com.company.controller;

import com.company.common.constants.Constant;
import com.company.dto.ResponeJson;
import com.company.entity.Product;
import com.company.entity.Specification;
import com.company.services.ProductService;
import com.company.services.SpecificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/spec")
@AllArgsConstructor
@Secured({"ROLE_SUPER_ADMIN", "ROLE_ADMIN"})
public class SpecificationController {
    SpecificationService specificationService;
    ProductService productService;

    @GetMapping("{id}")
    public Specification getByProductId(@RequestParam("id") Long id) {
        Product product = productService.findById(id);
        return specificationService.findByProduct(product);
    }

    @PutMapping("{id}")
    public ResponseEntity<Specification> updateSpec(@RequestBody Specification specification,
                                                   @RequestParam("id") Long id) {
        return ResponseEntity.ok(specificationService.update(specification, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponeJson<?>> deleteById(@PathVariable Long id) {
        specificationService.delete(id);
        return ResponseEntity.ok(new ResponeJson<>(HttpStatus.OK, Constant.SUCCESS));
    }
}

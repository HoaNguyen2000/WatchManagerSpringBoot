package com.company.services;

import com.company.dto.ProductResponse;
import com.company.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAllByJooq();

    Page<Product> findAll(Pageable pageable);

    Product save(Product product);

    List<Product> saveAll(List<Product> products);

    Product findById(Long id);

    Product update(Product product, Long id);

    void delete(Long id);

    Product uploadImageById(MultipartFile image, Long id);

    String uploadImageWithIMGBB(MultipartFile file);
}

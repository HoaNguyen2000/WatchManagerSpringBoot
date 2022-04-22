package com.company.services;

import com.company.common.utils.FileUtils;
import com.company.dto.ProductResponse;
import com.company.dto.ProductsDTO;
import com.company.entity.Product;
import com.company.exception.ResourceNotFoundExeption;
import com.company.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static jooq.demo.com.Tables.BRANDS;
import static jooq.demo.com.Tables.PRODUCTS;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    FileUtils fileUtils;
    private final DSLContext context;
    FileStorageService fileStorageService;

    @Override
    public List<ProductResponse> findAllByJooq() {
        return context.select(
                        PRODUCTS.ID,
                        PRODUCTS.BRAND_ID,
                        BRANDS.NAME.as("brandName"),
                        PRODUCTS.NAME,
                        PRODUCTS.OLD_PRICE,
                        PRODUCTS.PRICE,
                        PRODUCTS.SLUG,
                        PRODUCTS.DESCRIPTION)
                .from(PRODUCTS).
                innerJoin(BRANDS).on(PRODUCTS.BRAND_ID.eq(BRANDS.ID))
                .fetchInto(ProductResponse.class);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Product save(ProductsDTO product, MultipartFile file) {
        String fileName = fileStorageService.saveImage(file);

        Product productSave = new Product();
        productSave.setName(product.getName());
        productSave.setImageLink(fileName);
        productSave.setBrands(product.getBrands());
        productSave.setOldPrice(product.getOldPrice());
        productSave.setPrice(product.getPrice());
        productSave.setSlug(product.getSlug());
        productSave.setType(product.getType());
        productSave.setDescription(product.getDescription());

        return productRepository.save(productSave);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundExeption("Cannot found the resource")
        );
    }

    @Override
    @Transactional
    public Product update(Product product, Long id) {
        Product productUpdate = findById(id);

        productUpdate.setName(product.getName());
        productUpdate.setDescription(product.getDescription());
        productUpdate.setOldPrice(product.getOldPrice());
        productUpdate.setBrands(product.getBrands());
        productUpdate.setPrice(product.getPrice());
        productUpdate.setSlug(product.getSlug());
        productUpdate.setType(product.getType());

        return productRepository.save(productUpdate);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}

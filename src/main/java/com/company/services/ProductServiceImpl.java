package com.company.services;

import com.company.dto.ProductResponse;
import com.company.entity.Product;
import com.company.exception.ResourceNotFoundExeption;
import com.company.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static jooq.demo.com.Tables.BRANDS;
import static jooq.demo.com.Tables.PRODUCTS;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final DSLContext context;

    @Override
    public List<ProductResponse> findAll() {
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
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundExeption("Cannot found the resource")
        );
    }

    @Override
    public Product update(Product product, Long id) {
        Product productSave = findById(id);

        productSave.setName(product.getName());
        productSave.setDescription(product.getDescription());
        productSave.setOldPrice(product.getOldPrice());
        productSave.setBrandId(product.getBrandId());
        productSave.setPrice(product.getPrice());
        productSave.setSlug(product.getSlug());
        productSave.setType(product.getType());

        return productRepository.save(productSave);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}

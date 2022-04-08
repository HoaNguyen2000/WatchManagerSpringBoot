package com.company.services;

import com.company.entity.Product;
import com.company.exception.ResourceNotFoundExeption;
import com.company.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
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

    @Override
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}

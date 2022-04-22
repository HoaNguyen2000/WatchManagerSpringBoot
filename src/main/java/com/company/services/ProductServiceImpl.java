package com.company.services;

import com.company.dto.Data;
import com.company.dto.ImageImgbbResponse;
import com.company.dto.ProductResponse;
import com.company.dto.ProductsDTO;
import com.company.entity.Product;
import com.company.exception.ResourceNotFoundExeption;
import com.company.repository.ProductRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;

import static jooq.demo.com.Tables.BRANDS;
import static jooq.demo.com.Tables.PRODUCTS;

@Service

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final DSLContext context;

    public ProductServiceImpl(ProductRepository productRepository, DSLContext context) {
        this.productRepository = productRepository;
        this.context = context;
    }

    @Value("${imgbb.key}")
    private String key;

    WebClient client = WebClient.builder()
            .baseUrl("https://api.imgbb.com/1/upload?expiration=2592000&key=" + key)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();
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
        String imageLink = uploadImageWithIMGBB(file);

        Product productSave = new Product();
        productSave.setName(product.getName());
        productSave.setImageLink(imageLink);
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
    public Product update(Product product, MultipartFile image, Long id) {
        Product productUpdate = findById(id);
        String imageLink = uploadImageWithIMGBB(image);
        productUpdate.setName(product.getName());
        productUpdate.setDescription(product.getDescription());
        productUpdate.setOldPrice(product.getOldPrice());
        productUpdate.setBrands(product.getBrands());
        productUpdate.setPrice(product.getPrice());
        productUpdate.setSlug(product.getSlug());
        productUpdate.setType(product.getType());
        productUpdate.setImageLink(imageLink);

        return productRepository.save(productUpdate);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    @Override
    public String uploadImageWithIMGBB(MultipartFile file) {
        byte[] image = new byte[0];
        try {
            image = Base64.encodeBase64(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imageBase64 = new String(image);
        ImageImgbbResponse imageImgbbResponse = client.post()
                .body(BodyInserters.fromFormData("image", imageBase64))
                .retrieve()
                .bodyToMono(ImageImgbbResponse.class)
                .block();
        Data data = imageImgbbResponse.getData();
        return data.getDisplayUrl();
    }
}

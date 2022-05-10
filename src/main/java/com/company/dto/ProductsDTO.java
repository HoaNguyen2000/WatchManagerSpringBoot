package com.company.dto;

import com.company.entity.Brands;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;


@AllArgsConstructor
@Data
public class ProductsDTO implements Serializable {
    private String name;

    private long brandId;

    private String slug;

    private Double oldPrice;

    private Double price;

    private String type;

    private String description;

    MultipartFile image;
}

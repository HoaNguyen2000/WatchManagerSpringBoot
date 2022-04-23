package com.company.dto;

import com.company.entity.Brands;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;


@AllArgsConstructor
@Data
public class ProductsDTO {
    private String name;

    private Brands brands;

    private String slug;

    private Double oldPrice;

    private Double price;

    private String type;

    private String description;
}

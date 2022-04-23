package com.company.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductResponse {
    @JsonProperty("id")
    private int id;

    @JsonProperty("brand_id")
    private int brandId;

    @JsonProperty("brandName")
    private String brandName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("old_price")
    private double oldPrice;

    @JsonProperty("price")
    private double price;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("description")
    private String description;
}

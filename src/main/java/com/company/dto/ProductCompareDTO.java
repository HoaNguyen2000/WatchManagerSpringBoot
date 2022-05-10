package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ProductCompareDTO {
    private String name;
    private String price;
    private String link;
    private String image;
}

package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemsCartDashboardDTO {
    private Integer totalProduct;

    private Integer totalUsers;

    private Integer totalBrands;
}
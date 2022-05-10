package com.company.services;

import com.company.dto.ProductCompareDTO;

import java.util.List;

public interface ReportService {
    List<ProductCompareDTO> getProductsTGDD(String searchQuery);

    List<ProductCompareDTO> getProductsShopee(String searchQuery);
}

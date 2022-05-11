package com.company.services;

import com.company.dto.ProductCompareDTO;
import com.company.dto.tiki.DataTikiResponse;

import java.util.List;

public interface ReportService {
    List<ProductCompareDTO> getProductsTGDD(String searchQuery);

    DataTikiResponse getProductsTiki(String searchQuery);

    List<ProductCompareDTO> getProductsHaiTrieuWatch(String searchQuery);

    String getItemCart();
}

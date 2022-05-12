package com.company.services;

import com.company.dto.ItemsCartDashboardDTO;
import com.company.dto.ProductCompareDTO;
import com.company.dto.tiki.DataTikiResponse;

import java.util.List;

public interface ReportService {
    List<ProductCompareDTO> getProductsTGDD(String searchQuery);

    DataTikiResponse getProductsTiki(String searchQuery);

    ItemsCartDashboardDTO getItemCartDashboard();
}

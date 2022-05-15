package com.company.services;

import com.company.dto.ItemsCartDashboardDTO;
import com.company.dto.ProductCompareDTO;
import com.company.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

public interface ReportService {
    List<ProductCompareDTO> getProductsTGDD(String searchQuery);

    List<ProductCompareDTO> getProductsTiki(String searchQuery);

    ItemsCartDashboardDTO getItemCartDashboard();

    ByteArrayInputStream createExelProductsFile();

    List<Product> saveProductsByExcel(MultipartFile file) throws IOException;
}

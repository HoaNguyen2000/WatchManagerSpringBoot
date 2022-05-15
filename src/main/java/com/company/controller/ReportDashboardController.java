package com.company.controller;

import com.company.dto.ItemsCartDashboardDTO;
import com.company.dto.ProductCompareDTO;
import com.company.entity.Product;
import com.company.exception.BadRequestException;
import com.company.helper.ExelHelper;
import com.company.services.ReportService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/report")
class ReportDashboardController {

    private final ReportService reportService;
    private final ExelHelper exelHelper;

    ReportDashboardController(ReportService reportService, ExelHelper exelHelper) {
        this.reportService = reportService;
        this.exelHelper = exelHelper;
    }

    @GetMapping("/tgdd/{query}")
    public ResponseEntity<List<ProductCompareDTO>> crawDataTGDD(@PathVariable("query") String query) {
        return ResponseEntity.ok().body(reportService.getProductsTGDD(query));
    }

    @GetMapping("/tiki/{query}")
    public ResponseEntity<List<ProductCompareDTO>> getDataTiki(@PathVariable("query") String query) {
        return ResponseEntity.ok().body(reportService.getProductsTiki(query));
    }

    @GetMapping("item-cart-dashboard")
    public ResponseEntity<ItemsCartDashboardDTO> getItemsCartDashboard(){
        return ResponseEntity.ok().body(reportService.getItemCartDashboard());
    }

    @GetMapping("exel/products")
    public ResponseEntity<Resource> getFile() {
        String filename = "products_" + RandomStringUtils.randomAlphabetic(10) + ".xlsx";
        InputStreamResource file = new InputStreamResource(reportService.createExelProductsFile());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @PostMapping("excel/products")
    public ResponseEntity<List<Product>> saveProductsByExcel(@RequestParam("file") MultipartFile file){
        if (exelHelper.hasExcelFormat(file)) {
            try {
                return ResponseEntity.ok(reportService.saveProductsByExcel(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ResponseEntity.badRequest().body(null);
    }
}

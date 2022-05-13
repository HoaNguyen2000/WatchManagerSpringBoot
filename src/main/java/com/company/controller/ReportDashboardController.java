package com.company.controller;

import com.company.dto.ItemsCartDashboardDTO;
import com.company.dto.ProductCompareDTO;
import com.company.dto.tiki.DataTikiResponse;
import com.company.services.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1/report")
class ReportDashboardController {
    private final ReportService reportService;

    ReportDashboardController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/tgdd/{query}")
    public ResponseEntity<List<ProductCompareDTO>> crawDataTGDD(@PathVariable("query") String query) {
        return ResponseEntity.ok().body(reportService.getProductsTGDD(query));
    }

    @GetMapping("/tiki/{query}")
    public ResponseEntity<DataTikiResponse> getDataTiki(@PathVariable("query") String query) {
        return ResponseEntity.ok().body(reportService.getProductsTiki(query));
    }

    @GetMapping("item-cart-dashboard")
    public ResponseEntity<ItemsCartDashboardDTO> getItemsCartDashboard(){
        return ResponseEntity.ok().body(reportService.getItemCartDashboard());
    }
}

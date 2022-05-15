package com.company.services;

import com.company.common.utils.UrlUtils;
import com.company.dto.ItemsCartDashboardDTO;
import com.company.dto.ProductCompareDTO;
import com.company.dto.tiki.DataTikiResponse;
import com.company.entity.Product;
import com.company.helper.ExelHelper;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static jooq.demo.com.Tables.*;
import static org.jooq.impl.DSL.*;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {
    public static String URL_TGDD = "https://www.thegioididong.com/tim-kiem?key=";
    public static String URL_TIKI = "https://tiki.vn/api/v2/products?limit=20&include=advertisement&aggregations=2&trackity_id=4d80330e-cc30-2d02-a3ab-de05fce30b23&q=";

    private final UrlUtils urlUtils;
    private final WebClient webClient;
    private final DSLContext context;
    private final ProductService productService;
    private final ExelHelper exelHelper;
    public ReportServiceImpl(UrlUtils urlUtils,
                             WebClient.Builder webClientBuilder,
                             DSLContext context,
                             ProductService productService,
                             ExelHelper exelHelper) {
        this.urlUtils = urlUtils;
        this.webClient = webClientBuilder.build();
        this.context = context;
        this.productService = productService;
        this.exelHelper = exelHelper;
    }

    @Override
    public List<ProductCompareDTO> getProductsTGDD(String searchQuery) {
        List<ProductCompareDTO> products = new ArrayList<>();
        String url = URL_TGDD + searchQuery;
        try {
            Document document = Jsoup.connect(url).get();
            Elements elms = document.getElementsByClass("listproduct");
            for (Element elm : elms) {
                Elements elmRows = elm.getElementsByTag("li");
                for (Element element : elmRows) {
                    String name = element.getElementsByTag("h3").text();
                    String price = element.getElementsByTag("strong").text();
                    String productLink = "https://www.thegioididong.com" +
                            element.getElementsByTag("a").attr("href");
                    String imageLink = element.getElementsByTag("img").attr("data-src");
                    if (!name.equals("") && !price.equals("") && !imageLink.equals("")) {
                        products.add(new ProductCompareDTO(name, price, productLink, imageLink));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<ProductCompareDTO> getProductsTiki(String searchQuery) {
        List<ProductCompareDTO> productCompareDTOS = new ArrayList<>();
        String urlEncode = urlUtils.encodeString(searchQuery);
        String fullUrl = URL_TIKI + urlEncode;
        DataTikiResponse dataTikiResponse = webClient.get()
                .uri(fullUrl)
                .retrieve()
                .bodyToMono(DataTikiResponse.class)
                .block();
        dataTikiResponse.getData().forEach((item) -> productCompareDTOS.add(new ProductCompareDTO(
                item.getName(),
                item.getOriginalPrice(),
                item.getUrlPath(),
                item.getThumbnailUrl())));
        return productCompareDTOS;
    }


    @Override
    public ItemsCartDashboardDTO getItemCartDashboard() {
        int totalProduct = context.select(count()).from(PRODUCTS).fetchOne(0, int.class);
        int totalUsers = context.select(count()).from(USERS).fetchOne(0, int.class);
        int totalBrands = context.select(count()).from(BRANDS).fetchOne(0, int.class);

        return new ItemsCartDashboardDTO(totalProduct, totalUsers, totalBrands);
    }

    @Override
    @Transactional
    public ByteArrayInputStream createExelProductsFile() {
        Pageable pageable = PageRequest.of(0, 30);
        Page<Product> products = productService.findAll(pageable);
        return exelHelper.productsToExcel(products.getContent());
    }

    @Override@Transactional
    public List<Product> saveProductsByExcel(MultipartFile file)  {
        List<Product> response = new ArrayList<>();
        try {
            var products = exelHelper.excelToProducts(file.getInputStream());
            response = productService.saveAll(products);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }
}

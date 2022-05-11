package com.company.services;

import com.company.common.utils.UrlUtils;
import com.company.dto.ProductCompareDTO;
import com.company.dto.tiki.DataTikiResponse;
import lombok.extern.slf4j.Slf4j;
import org.jooq.DSLContext;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static jooq.demo.com.Tables.*;
import static org.jooq.impl.DSL.count;

@Slf4j
@Service
public class ReportServiceImpl implements ReportService {
    public static String URL_TGDD = "https://www.thegioididong.com/tim-kiem?key=";
    public static String URL_TIKI = "https://tiki.vn/api/v2/products?limit=48&include=advertisement&aggregations=2&trackity_id=4d80330e-cc30-2d02-a3ab-de05fce30b23&q=";
    public static String URL_DANGQUANG = "https://donghohaitrieu.com/search-results?q=";
    private final UrlUtils urlUtils;
    private final WebClient webClient;
    private final DSLContext context;

    public ReportServiceImpl(UrlUtils urlUtils, WebClient.Builder webClientBuilder, DSLContext context) {
        this.urlUtils = urlUtils;
        this.webClient = webClientBuilder.build();
        this.context = context;
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

                    products.add(new ProductCompareDTO(name, price, productLink, imageLink));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public DataTikiResponse getProductsTiki(String searchQuery) {
        String urlEncode = urlUtils.encodeString(searchQuery);
        String fullUrl = URL_TIKI + urlEncode;
        return webClient.get()
                .uri(fullUrl)
                .retrieve()
                .bodyToMono(DataTikiResponse.class)
                .block();
    }

    @Override
    public List<ProductCompareDTO> getProductsHaiTrieuWatch(String searchQuery) {
        List<ProductCompareDTO> products = new ArrayList<>();
        String url = URL_DANGQUANG + searchQuery;
        try {
            Document document = Jsoup.connect(url).get();
            Elements elms = document.getElementsByClass("snize-search-results-content clearfix");
            for (Element elm : elms) {
                Elements elmRows = elm.getElementsByTag("li");
                for (Element element : elmRows) {
                    String name = element.getElementsByClass("snize-title").text();
                    String price = element.getElementsByClass("snize-price  money").text();
                    String productLink = element.getElementsByClass("snize-view-link").attr("href");
                    String imageLink = element.getElementsByTag("img").attr("src");

                    products.add(new ProductCompareDTO(name, price, productLink, imageLink));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public String getItemCart() {
        context.select(
                count(PRODUCTS).as("totalProduct"),
                count(USERS).as("totalUsers"),
                count(BRANDS).as("totalBrands")
        ).from(PRODUCTS);
        return null;
    }
}

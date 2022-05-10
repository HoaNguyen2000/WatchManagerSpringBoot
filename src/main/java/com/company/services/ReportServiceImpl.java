package com.company.services;

import com.company.common.utils.UrlUtils;
import com.company.dto.ProductCompareDTO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    public static String URL_TGDD = "https://www.thegioididong.com/tim-kiem?key=";
    private final UrlUtils urlUtils;

    public ReportServiceImpl(UrlUtils urlUtils) {
        this.urlUtils = urlUtils;
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
    public List<ProductCompareDTO> getProductsShopee(String searchQuery) {
        String encode = urlUtils.encodeString(searchQuery);
        return null;
    }
}

package com.company.dto.tiki;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemsTiki {

    @JsonProperty("name")
    private String name;

    @JsonProperty("url_path")
    private String urlPath;

    @JsonProperty("original_price")
    private String originalPrice;

    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;
}
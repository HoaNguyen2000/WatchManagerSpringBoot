package com.company.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    @JsonProperty("id")
    private String id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("url_viewer")
    private String urlViewer;

    @JsonProperty("url")
    private String url;

    @JsonProperty("display_url")
    private String displayUrl;

    @JsonProperty("width")
    private String width;

    @JsonProperty("height")
    private String height;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("time")
    private String time;

    @JsonProperty("expiration")
    private String expiration;

    @JsonProperty("delete_url")
    private String deleteUrl;
}

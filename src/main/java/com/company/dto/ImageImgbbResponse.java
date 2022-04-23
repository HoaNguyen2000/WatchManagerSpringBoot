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
public class ImageImgbbResponse {

    @JsonProperty("data")
    private Data data;

    @JsonProperty("success")
    private Boolean success;

    @JsonProperty("status")
    private Integer status;

}


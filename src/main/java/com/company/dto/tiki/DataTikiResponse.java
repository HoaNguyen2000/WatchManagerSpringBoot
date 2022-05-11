package com.company.dto.tiki;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataTikiResponse {
    @JsonProperty("data")
    private List<ItemsTiki> data;
}

package com.hh.core.tool.merge.data;

import lombok.Data;

@Data
public class ProductDataDto {

    private Integer id;

    private String product;

    private String model;

    private Integer count;

    public ProductDataDto(Integer id, String product, String model, Integer count) {
        this.id = id;
        this.product = product;
        this.model = model;
        this.count = count;
    }
}

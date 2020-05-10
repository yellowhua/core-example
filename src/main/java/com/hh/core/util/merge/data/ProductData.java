package com.hh.core.util.merge.data;

import lombok.Data;

@Data
public class ProductData {

    private Integer id;

    private String product;

    private String model;

    private Integer count;

    public ProductData(Integer id, String product, String model, Integer count) {
        this.id = id;
        this.product = product;
        this.model = model;
        this.count = count;
    }
}

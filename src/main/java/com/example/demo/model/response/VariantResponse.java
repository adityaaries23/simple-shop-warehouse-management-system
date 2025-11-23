package com.example.demo.model.response;

import com.example.demo.model.entity.Pricing;
import com.example.demo.model.entity.Stock;
import com.example.demo.model.entity.Variant;
import lombok.Data;

@Data
public class VariantResponse {
    private Long variantId;
    private String size;
    private String color;
    private String sku;
    private String price;
    private Integer quantity;
    private String currency;

    public VariantResponse(Variant variant, Stock stock, Pricing  pricing) {
        this.variantId = variant.getId();
        this.size = variant.getSize();
        this.color = variant.getColor();
        this.sku = variant.getSku();
        this.price = pricing.getPrice().toString();
        this.quantity = stock.getQuantity();
        this.currency =  pricing.getCurrency();
    }
}

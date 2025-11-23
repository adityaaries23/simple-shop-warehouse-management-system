package com.example.demo.model.response;

import com.example.demo.model.entity.Item;
import lombok.Data;

import java.util.List;

@Data
public class ItemDetailResponse {
    private Long id;
    private String name;
    private String description;
    private List<VariantResponse> variants;

    public ItemDetailResponse(Item item, List<VariantResponse> variants) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.variants = variants;
    }
}

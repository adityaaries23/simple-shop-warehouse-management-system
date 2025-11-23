package com.example.demo.model.response;

import com.example.demo.projections.ItemProjections;
import lombok.Data;

@Data
public class ItemResponse {
    private Long itemId;
    private String itemName;
    private String itemDescription;
    private String itemPrice;
    private Integer itemQuantity;

    public ItemResponse(ItemProjections itemProjections) {
        this.itemId = itemProjections.getItemId();
        this.itemName = itemProjections.getItemName();
        this.itemDescription = itemProjections.getItemDescription();
        this.itemQuantity = itemProjections.getItemQuantity();
        if (itemProjections.getMinPrice().equals(itemProjections.getMaxPrice())) {
            this.itemPrice = itemProjections.getMaxPrice().toString();
        } else {
            this.itemPrice = itemProjections.getMinPrice().toString() + " - " + itemProjections.getMaxPrice().toString();
        }
    }
}

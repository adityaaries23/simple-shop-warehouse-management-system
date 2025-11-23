package com.example.demo.projections;

import java.math.BigDecimal;

public interface ItemProjections {
    Long getItemId();
    String getItemName();
    String getItemDescription();
    BigDecimal getMinPrice();
    BigDecimal getMaxPrice();
    Integer getItemQuantity();
}

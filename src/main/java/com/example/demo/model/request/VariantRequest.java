package com.example.demo.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class VariantRequest {
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    private String color;
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 50)
    private String size;

    @NotNull
    private BigDecimal price;

    @NotNull
    private String currency;

    @NotNull
    private Integer quantity;
}

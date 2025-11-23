package com.example.demo.model.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class VariantUpdateRequest extends VariantRequest {
    private Long variantId;
}

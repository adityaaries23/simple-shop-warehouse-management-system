package com.example.demo.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class ItemUpdateRequest {
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @NotEmpty
    @NotNull
    private String description;

    @NotNull
    @NotEmpty
    @Valid
    List<VariantUpdateRequest> variants;
}

package com.example.demo.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ItemRequest {
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 255)
    private String name;
    @NotEmpty
    @NotNull
    private String description;
}

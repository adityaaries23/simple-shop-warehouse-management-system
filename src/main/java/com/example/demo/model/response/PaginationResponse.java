package com.example.demo.model.response;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class PaginationResponse {
    int totalPages;
    int currentPage;
    int pageSize;
    Object content;

    public PaginationResponse(Page<?> page, Object content) {
        this.content = content;
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
        this.currentPage = page.getNumber();
        this.pageSize = page.getSize();
    }
}

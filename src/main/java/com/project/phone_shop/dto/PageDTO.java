package com.project.phone_shop.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageDTO {
    private List<?> list;
    private PaginationDTO pagination;

    public PageDTO(Page<?> page) {
        this.list = page.getContent();
        this.pagination = PaginationDTO.builder()
                .empty(page.isEmpty())
                .first(page.isFirst())
                .last(page.isLast())
                .pageSize(page.getPageable().getPageSize())
                .pageNumber(page.getPageable().getPageNumber() + 1)
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .numberOfElements(page.getNumberOfElements())
                .build();
    }
}

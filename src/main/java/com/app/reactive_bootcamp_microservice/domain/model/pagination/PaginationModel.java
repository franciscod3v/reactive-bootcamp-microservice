package com.app.reactive_bootcamp_microservice.domain.model.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationModel<T> {
    private List<T> content;
    private int page;
    private int size;
    private boolean nextPage;
    private long totalElements;
    private int totalPages;


}

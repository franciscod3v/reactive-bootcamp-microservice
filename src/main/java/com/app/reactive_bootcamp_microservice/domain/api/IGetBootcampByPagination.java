package com.app.reactive_bootcamp_microservice.domain.api;

import com.app.reactive_bootcamp_microservice.domain.model.pagination.BootcampBasic;
import com.app.reactive_bootcamp_microservice.domain.model.pagination.PaginationModel;
import reactor.core.publisher.Mono;

public interface IGetBootcampByPagination {
    Mono<PaginationModel<BootcampBasic>> getAllBootcamps(String sortBy, Boolean asc, Integer numberPage, Integer sizePage);
}

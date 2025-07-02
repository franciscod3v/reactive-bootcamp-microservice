package com.app.reactive_bootcamp_microservice.domain.spi;

import com.app.reactive_bootcamp_microservice.domain.model.Bootcamp;
import com.app.reactive_bootcamp_microservice.domain.model.pagination.BootcampBasic;
import com.app.reactive_bootcamp_microservice.domain.model.pagination.PaginationModel;
import reactor.core.publisher.Mono;

public interface IBootcampPersistencePort {
    Mono<Bootcamp> createBootcamp(Bootcamp bootcamp);
    Mono<PaginationModel<Bootcamp>> getAllBootcamps(String sortBy, Boolean asc, Integer numberPage, Integer sizePage);
}

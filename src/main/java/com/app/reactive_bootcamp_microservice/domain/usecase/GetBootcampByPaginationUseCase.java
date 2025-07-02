package com.app.reactive_bootcamp_microservice.domain.usecase;

import com.app.reactive_bootcamp_microservice.domain.api.IGetBootcampByPagination;
import com.app.reactive_bootcamp_microservice.domain.model.Bootcamp;
import com.app.reactive_bootcamp_microservice.domain.model.pagination.BootcampBasic;
import com.app.reactive_bootcamp_microservice.domain.model.pagination.PaginationModel;
import com.app.reactive_bootcamp_microservice.domain.spi.IBootcampPersistencePort;
import com.app.reactive_bootcamp_microservice.domain.spi.ICapabilityGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class GetBootcampByPaginationUseCase implements IGetBootcampByPagination {

    private final IBootcampPersistencePort bootcampPersistencePort;
    private final ICapabilityGateway capabilityGateway;

    @Override
    public Mono<PaginationModel<BootcampBasic>> getAllBootcamps(String sortBy, Boolean asc, Integer numberPage, Integer sizePage) {
        return bootcampPersistencePort.getAllBootcamps(sortBy, asc, numberPage, sizePage)
                .flatMap(pagination -> {
                    List<Bootcamp> bootcamps = pagination.getContent();

                    return Flux.fromIterable(bootcamps)
                            .flatMap(bootcamp ->
                                    capabilityGateway.getCapabilitiesWithTechnologiesByBootcampId(bootcamp.getId())
                                            .collectList()
                                            .map(capabilities -> new BootcampBasic(
                                                    bootcamp.getId(),
                                                    bootcamp.getName(),
                                                    bootcamp.getDescription(),
                                                    bootcamp.getLaunchDate(),
                                                    bootcamp.getDurationInDays(),
                                                    capabilities
                                            ))
                            )
                            .collectList()
                            .map(bootcampBasics -> PaginationModel.<BootcampBasic>builder()
                                    .content(bootcampBasics)
                                    .page(pagination.getPage())
                                    .size(pagination.getSize())
                                    .nextPage(pagination.isNextPage())
                                    .totalElements(pagination.getTotalElements())
                                    .totalPages(pagination.getTotalPages())
                                    .build());
                });
    }

}

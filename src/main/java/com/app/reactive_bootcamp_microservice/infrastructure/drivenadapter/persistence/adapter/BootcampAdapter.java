package com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.persistence.adapter;

import com.app.reactive_bootcamp_microservice.domain.model.Bootcamp;
import com.app.reactive_bootcamp_microservice.domain.model.pagination.BootcampBasic;
import com.app.reactive_bootcamp_microservice.domain.model.pagination.PaginationModel;
import com.app.reactive_bootcamp_microservice.domain.spi.IBootcampPersistencePort;
import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.persistence.mapper.BootcampEntityMapper;
import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.persistence.repository.IBootcampRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BootcampAdapter implements IBootcampPersistencePort {

    private final IBootcampRepository bootcampRepository;
    private final BootcampEntityMapper mapper;

    @Override
    public Mono<Bootcamp> createBootcamp(Bootcamp bootcamp) {

        return bootcampRepository
                .save(mapper.toEntity(bootcamp))
                .map(mapper::toDomain);
    }

    @Override
    public Mono<PaginationModel<Bootcamp>> getAllBootcamps(String sortBy, Boolean asc, Integer numberPage, Integer sizePage) {

        Sort.Direction direction = asc ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(numberPage, sizePage, sort);

        return bootcampRepository.findAllBy(pageable)
                .map(mapper::toDomain)
                .collectList()
                .zipWith(bootcampRepository.count())
                .map(tuple -> {
                    var content = tuple.getT1();
                    long totalElements = tuple.getT2();
                    int totalPages = (int) Math.ceil((double) totalElements/sizePage);
                    boolean hasNext = (numberPage + 1) < totalPages;

                    return PaginationModel.<Bootcamp>builder()
                            .content(content)
                            .page(numberPage)
                            .size(sizePage)
                            .nextPage(hasNext)
                            .totalElements(totalElements)
                            .totalPages(totalPages)
                            .build();
                });
    }
}

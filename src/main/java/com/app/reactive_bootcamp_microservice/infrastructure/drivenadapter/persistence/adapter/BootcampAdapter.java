package com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.persistence.adapter;

import com.app.reactive_bootcamp_microservice.domain.model.Bootcamp;
import com.app.reactive_bootcamp_microservice.domain.spi.ICreateBootcampPersistencePort;
import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.persistence.mapper.BootcampEntityMapper;
import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.persistence.repository.IBootcampRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class BootcampAdapter implements ICreateBootcampPersistencePort {

    private final IBootcampRepository bootcampRepository;
    private final BootcampEntityMapper mapper;

    @Override
    public Mono<Bootcamp> createBootcamp(Bootcamp bootcamp) {

        return bootcampRepository
                .save(mapper.toEntity(bootcamp))
                .map(mapper::toDomain);
    }
}

package com.app.reactive_bootcamp_microservice.domain.usecase;

import com.app.reactive_bootcamp_microservice.domain.api.ICreateBootcampServicePort;
import com.app.reactive_bootcamp_microservice.domain.model.Bootcamp;
import com.app.reactive_bootcamp_microservice.domain.spi.ICreateBootcampPersistencePort;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CreateBootcampUseCase implements ICreateBootcampServicePort {

    private final ICreateBootcampPersistencePort persistenceAdapter;

    @Override
    public Mono<Bootcamp> createBootcamp(Bootcamp bootcamp) {
        return persistenceAdapter
                .createBootcamp(bootcamp);
    }
}

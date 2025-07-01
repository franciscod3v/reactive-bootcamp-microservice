package com.app.reactive_bootcamp_microservice.domain.spi;

import com.app.reactive_bootcamp_microservice.domain.model.Bootcamp;
import reactor.core.publisher.Mono;

public interface ICreateBootcampPersistencePort {
    Mono<Bootcamp> createBootcamp(Bootcamp bootcamp);
}

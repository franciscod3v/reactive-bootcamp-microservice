package com.app.reactive_bootcamp_microservice.domain.api;

import com.app.reactive_bootcamp_microservice.domain.model.Bootcamp;
import reactor.core.publisher.Mono;

public interface ICreateBootcampServicePort {
    Mono<Bootcamp> createBootcamp(Bootcamp bootcamp);
}

package com.app.reactive_bootcamp_microservice.domain.spi;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICapabilityGateway {
    Mono<Void> createBootcampCapabilityAssociations(Long capabilityId, Flux<Long> capabilitiesIds);
}

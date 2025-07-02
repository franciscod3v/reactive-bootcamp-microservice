package com.app.reactive_bootcamp_microservice.domain.spi;

import com.app.reactive_bootcamp_microservice.domain.model.pagination.CapabilityBasic;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICapabilityGateway {
    Mono<Void> createBootcampCapabilityAssociations(Long capabilityId, Flux<Long> capabilitiesIds);
    Flux<CapabilityBasic> getCapabilitiesWithTechnologiesByBootcampId(Long bootcampId);
}

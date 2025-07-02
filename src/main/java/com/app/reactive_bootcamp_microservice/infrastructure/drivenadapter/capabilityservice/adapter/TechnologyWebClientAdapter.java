package com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.capabilityservice.adapter;

import com.app.reactive_bootcamp_microservice.domain.model.pagination.CapabilityBasic;
import com.app.reactive_bootcamp_microservice.domain.spi.ICapabilityGateway;
import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.capabilityservice.dto.BootcampCapabilityDTO;
import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.capabilityservice.dto.CapabilityWithTechnologiesDTO;
import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.capabilityservice.mapper.CapabilityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
public class TechnologyWebClientAdapter implements ICapabilityGateway {

    private final WebClient webClient;
    private final CapabilityMapper mapper;

    @Override
    public Mono<Void> createBootcampCapabilityAssociations(Long bootcampId, Flux<Long> capabilitiesIds) {
        return capabilitiesIds
                .collectList()
                .map(ids -> {
                    return new BootcampCapabilityDTO(bootcampId, ids);
                })
                .map(List::of)
                .flatMap(dtoList ->
                        webClient
                                .post()
                                .uri("/api/bootcamp-capa")
                                .bodyValue(dtoList)
                                .retrieve()
                                .bodyToMono(Void.class)
                );
    }

    @Override
    public Flux<CapabilityBasic> getCapabilitiesWithTechnologiesByBootcampId(Long bootcampId) {
        return webClient
                .get()
                .uri("/api/capabilities/by-bootcamp/{bootcampId}", bootcampId)
                .retrieve()
                .bodyToFlux(CapabilityWithTechnologiesDTO.class)
                .map(mapper::toModel);
    }
}

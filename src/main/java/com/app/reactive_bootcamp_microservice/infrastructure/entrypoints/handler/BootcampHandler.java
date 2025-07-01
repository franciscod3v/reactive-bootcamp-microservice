package com.app.reactive_bootcamp_microservice.infrastructure.entrypoints.handler;

import com.app.reactive_bootcamp_microservice.domain.api.ICreateBootcampServicePort;
import com.app.reactive_bootcamp_microservice.domain.model.Bootcamp;
import com.app.reactive_bootcamp_microservice.domain.spi.ICapabilityGateway;
import com.app.reactive_bootcamp_microservice.infrastructure.entrypoints.dto.BootcampRequestDTO;
import com.app.reactive_bootcamp_microservice.infrastructure.entrypoints.mapper.BootcampMapperDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class BootcampHandler implements IBootcampHandler{

    private final ICreateBootcampServicePort bootcampServicePort;
    private final ICapabilityGateway capabilityGateway;
    private final BootcampMapperDTO mapper;

    @Override
    public Mono<ServerResponse> listenPOSTCreateBootCamp(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(BootcampRequestDTO.class)
                .flatMap(dto -> {
                    Bootcamp model = mapper.toModel(dto);
                    return bootcampServicePort.createBootcamp(model)
                            .flatMap(saved ->
                                    capabilityGateway.createBootcampCapabilityAssociations(
                                            saved.getId(),
                                            Flux.fromIterable(dto.getCapabilityIds())
                                    ).then(ServerResponse.created(URI.create("/api/bootcamp/" + saved.getId())).build())
                            );
                });
    }
}

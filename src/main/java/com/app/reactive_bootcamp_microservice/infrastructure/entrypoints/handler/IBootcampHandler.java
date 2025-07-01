package com.app.reactive_bootcamp_microservice.infrastructure.entrypoints.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface IBootcampHandler {
    Mono<ServerResponse> listenPOSTCreateBootCamp(ServerRequest serverRequest);
}

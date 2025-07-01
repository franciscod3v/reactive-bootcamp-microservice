package com.app.reactive_bootcamp_microservice.infrastructure.entrypoints.router;

import com.app.reactive_bootcamp_microservice.infrastructure.entrypoints.handler.IBootcampHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BootcampRouterRest {

    @Bean(name = "bootcampRouterRestBean")
    public RouterFunction<ServerResponse> bootcampRouter (IBootcampHandler handler) {
        return route(POST("/api/bootcamp"), handler::listenPOSTCreateBootCamp);
    }
}

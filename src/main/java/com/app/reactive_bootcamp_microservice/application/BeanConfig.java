package com.app.reactive_bootcamp_microservice.application;

import com.app.reactive_bootcamp_microservice.domain.api.ICreateBootcampServicePort;
import com.app.reactive_bootcamp_microservice.domain.spi.ICapabilityGateway;
import com.app.reactive_bootcamp_microservice.domain.spi.ICreateBootcampPersistencePort;
import com.app.reactive_bootcamp_microservice.domain.usecase.CreateBootcampUseCase;
import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.capabilityservice.adapter.TechnologyWebClientAdapter;
import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.persistence.adapter.BootcampAdapter;
import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.persistence.mapper.BootcampEntityMapper;
import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.persistence.repository.IBootcampRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanConfig {

    @Bean
    public WebClient webClient(@Value("${capability-service.base-url}") String baseUrl) {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    @Bean
    public ICapabilityGateway capabilityGateway(WebClient webClient) {
        return new TechnologyWebClientAdapter(webClient);
    }

    @Bean
    public ICreateBootcampPersistencePort createBootcampPersistencePort(
            IBootcampRepository bootcampRepository,
            BootcampEntityMapper mapper
    ) {
        return new BootcampAdapter(bootcampRepository, mapper);
    }

    @Bean
    public ICreateBootcampServicePort createBootcampServicePort(
            ICreateBootcampPersistencePort persistencePort
    ) {
        return new CreateBootcampUseCase(persistencePort);
    }


}

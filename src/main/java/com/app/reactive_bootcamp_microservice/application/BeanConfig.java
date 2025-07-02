package com.app.reactive_bootcamp_microservice.application;

import com.app.reactive_bootcamp_microservice.domain.api.ICreateBootcampServicePort;
import com.app.reactive_bootcamp_microservice.domain.api.IGetBootcampByPagination;
import com.app.reactive_bootcamp_microservice.domain.spi.ICapabilityGateway;
import com.app.reactive_bootcamp_microservice.domain.spi.IBootcampPersistencePort;
import com.app.reactive_bootcamp_microservice.domain.usecase.CreateBootcampUseCase;
import com.app.reactive_bootcamp_microservice.domain.usecase.GetBootcampByPaginationUseCase;
import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.capabilityservice.adapter.TechnologyWebClientAdapter;
import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.capabilityservice.mapper.CapabilityMapper;
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
    public ICapabilityGateway capabilityGateway(WebClient webClient, CapabilityMapper mapper) {
        return new TechnologyWebClientAdapter(webClient, mapper);
    }

    @Bean
    public IBootcampPersistencePort createBootcampPersistencePort(
            IBootcampRepository bootcampRepository,
            BootcampEntityMapper mapper
    ) {
        return new BootcampAdapter(bootcampRepository, mapper);
    }

    @Bean
    public ICreateBootcampServicePort createBootcampServicePort(
            IBootcampPersistencePort persistencePort
    ) {
        return new CreateBootcampUseCase(persistencePort);
    }

    @Bean
    public IGetBootcampByPagination getBootcampByPaginationService(
            IBootcampPersistencePort persistencePort,
            ICapabilityGateway capabilityGateway
    ) {
        return new GetBootcampByPaginationUseCase(persistencePort, capabilityGateway);
    }


}

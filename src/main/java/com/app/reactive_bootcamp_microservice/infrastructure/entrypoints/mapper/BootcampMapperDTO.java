package com.app.reactive_bootcamp_microservice.infrastructure.entrypoints.mapper;

import com.app.reactive_bootcamp_microservice.domain.model.Bootcamp;
import com.app.reactive_bootcamp_microservice.infrastructure.entrypoints.dto.BootcampRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

@Component
public class BootcampMapperDTO {
    private static final ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

    public Bootcamp toModel (BootcampRequestDTO dto) {
        return MAPPER.convertValue(dto, Bootcamp.class);
    }
}

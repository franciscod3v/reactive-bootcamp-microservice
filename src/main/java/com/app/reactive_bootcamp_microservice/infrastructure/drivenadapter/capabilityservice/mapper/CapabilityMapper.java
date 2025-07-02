package com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.capabilityservice.mapper;

import com.app.reactive_bootcamp_microservice.domain.model.pagination.CapabilityBasic;
import com.app.reactive_bootcamp_microservice.domain.model.pagination.TechnologyBasic;
import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.capabilityservice.dto.CapabilityWithTechnologiesDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CapabilityMapper {

    public CapabilityBasic toModel(CapabilityWithTechnologiesDTO dto) {
        return new CapabilityBasic(
                dto.getId(),
                dto.getName(),
                dto.getTechnologies()
                        .stream()
                        .map(tech -> new TechnologyBasic(tech.getId(), tech.getName()))
                        .collect(Collectors.toList())
        );
    }
}

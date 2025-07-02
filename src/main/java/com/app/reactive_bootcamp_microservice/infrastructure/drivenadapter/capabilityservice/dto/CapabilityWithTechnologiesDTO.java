package com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.capabilityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CapabilityWithTechnologiesDTO {
    private Long id;
    private String name;
    private String description;
    private List<TechnologyDTO> technologies;
}

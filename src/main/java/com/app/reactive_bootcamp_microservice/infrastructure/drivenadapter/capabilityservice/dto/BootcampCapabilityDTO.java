package com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.capabilityservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BootcampCapabilityDTO {
    private Long idBootcamp;
    private List<Long> capabilitiesIds;
}

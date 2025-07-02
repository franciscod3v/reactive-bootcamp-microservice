package com.app.reactive_bootcamp_microservice.domain.model.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BootcampBasic {
    private Long id;
    private String name;
    private String description;
    private LocalDate launchDate;
    private Integer durationInDays;
    private List<CapabilityBasic> capabilities;
}

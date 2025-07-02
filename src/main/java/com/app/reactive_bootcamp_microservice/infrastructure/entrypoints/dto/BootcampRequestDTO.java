package com.app.reactive_bootcamp_microservice.infrastructure.entrypoints.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BootcampRequestDTO {
    @NotBlank(message = "The name is required")
    @Size(max = 50, message = "The name can only have 50 characters")
    private String name;

    @NotBlank(message = "The description is required")
    @Size(max = 90, message = "The description can only have 90 characters")
    private String description;

    @NotNull(message = "The launch date is required")
    private LocalDate launchDate;

    @NotNull(message = "The duration in days is required")
    @Min(value = 1, message = "The duration must be greater than 0")
    private Integer durationInDays;

    @NotNull(message = "The list of capabilities is required")
    @Size(min = 1, message = "A bootcamp must have at least 1 capability")
    @Size(max = 4, message = "A bootcamp can have a maximum of 4 capabilities")
    private List<@NotNull(message = "Each capability ID must be valid") Long> capabilityIds;
}

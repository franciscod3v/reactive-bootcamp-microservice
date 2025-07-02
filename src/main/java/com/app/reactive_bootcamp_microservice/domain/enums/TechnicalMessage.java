package com.app.reactive_bootcamp_microservice.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TechnicalMessage {

    // Business Errors
    MINIMUM_CAPABILITIES_REQUIRED("400", "A bootcamp must have at least 1 capability", "capabilityIds"),
    MAXIMUM_CAPABILITIES_ALLOWED("400", "A bootcamp can have a maximum of 4 capabilities", "capabilityIds"),
    CAPABILITIES_REQUIRED("400", "The capabilities Ids are required", "capabilityIds"),

    // Validation Errors
    NAME_REQUIRED("400", "The name is required", "name"),
    MAXIMUM_CHARACTERS_FOR_NAME("400", "The name can only have 50 characters", "name"),

    DESCRIPTION_REQUIRED("400", "The description is required", "description"),
    MAXIMUM_CHARACTERS_FOR_DESCRIPTION("400", "The description can only have 90 characters", "description"),
    LAUNCH_DATE_REQUIRED("400", "The launch date is required", "launchDate"),
    DURATION_REQUIRED("400", "The duration in days is required", "durationInDays"),
    INVALID_DURATION("400", "The duration must be greater than 0", "durationInDays"),

    // Generic Errors
    INTERNAL_ERROR("500", "An unexpected error occurred", ""),
    BAD_REQUEST("400", "The request is malformed or invalid", "");

    private final String code;
    private final String message;
    private final String param;

}

package com.app.reactive_bootcamp_microservice.infrastructure.entrypoints.validators;
import com.app.reactive_bootcamp_microservice.domain.enums.TechnicalMessage;
import com.app.reactive_bootcamp_microservice.domain.exception.TechnicalException;
import com.app.reactive_bootcamp_microservice.infrastructure.entrypoints.dto.BootcampRequestDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DtoValidator {

    private final Validator validator;

    public Mono<BootcampRequestDTO> validateDto(BootcampRequestDTO dto) {
        Set<ConstraintViolation<BootcampRequestDTO>> violations = validator.validate(dto);
        if (violations.isEmpty()) return Mono.just(dto);

        ConstraintViolation<BootcampRequestDTO> violation = violations.iterator().next(); // primer error
        TechnicalMessage technicalMessage = mapViolationToTechnicalMessage(violation);
        return Mono.error(new TechnicalException(technicalMessage));
    }

    private TechnicalMessage mapViolationToTechnicalMessage(ConstraintViolation<?> violation) {
        String field = violation.getPropertyPath().toString();
        Object annotation = violation.getConstraintDescriptor().getAnnotation();

        switch (field) {
            case "name":
                if (annotation instanceof NotBlank) return TechnicalMessage.NAME_REQUIRED;
                if (annotation instanceof Size) return TechnicalMessage.MAXIMUM_CHARACTERS_FOR_NAME;
                break;

            case "description":
                if (annotation instanceof NotBlank) return TechnicalMessage.DESCRIPTION_REQUIRED;
                if (annotation instanceof Size) return TechnicalMessage.MAXIMUM_CHARACTERS_FOR_DESCRIPTION;
                break;

            case "launchDate":
                if (annotation instanceof NotNull) return TechnicalMessage.LAUNCH_DATE_REQUIRED;
                break;

            case "durationInDays":
                if (annotation instanceof NotNull) return TechnicalMessage.DURATION_REQUIRED;
                if (annotation instanceof Min) return TechnicalMessage.INVALID_DURATION;
                break;

            case "capabilityIds":
                if (annotation instanceof NotNull) return TechnicalMessage.CAPABILITIES_REQUIRED;
                if (annotation instanceof Size size) {
                    if (size.min() == 1) return TechnicalMessage.MINIMUM_CAPABILITIES_REQUIRED;
                    if (size.max() == 4) return TechnicalMessage.MAXIMUM_CAPABILITIES_ALLOWED;
                }
                break;
        }

        return TechnicalMessage.INTERNAL_ERROR;
    }
}

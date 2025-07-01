package com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.persistence.mapper;

import com.app.reactive_bootcamp_microservice.domain.model.Bootcamp;
import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.persistence.entity.BootcampEntity;
import org.springframework.stereotype.Component;

@Component
public class BootcampEntityMapper {
    public BootcampEntity toEntity(Bootcamp bootcamp) {
        BootcampEntity entity = new BootcampEntity();
        entity.setId(bootcamp.getId());
        entity.setName(bootcamp.getName());
        entity.setDescription(bootcamp.getDescription());
        entity.setLaunchDate(bootcamp.getLaunchDate());
        entity.setDurationInDays(bootcamp.getDurationInDays());
        return entity;
    }

    public Bootcamp toDomain(BootcampEntity bootcampEntity) {
        return new Bootcamp(
                bootcampEntity.getId(),
                bootcampEntity.getName(),
                bootcampEntity.getDescription(),
                bootcampEntity.getLaunchDate(),
                bootcampEntity.getDurationInDays(),
                null
        );
    }
}

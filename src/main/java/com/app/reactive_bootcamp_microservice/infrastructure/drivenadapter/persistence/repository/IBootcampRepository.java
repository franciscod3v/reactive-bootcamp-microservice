package com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.persistence.repository;

import com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.persistence.entity.BootcampEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface IBootcampRepository extends R2dbcRepository<BootcampEntity, Long> {
}

package com.app.reactive_bootcamp_microservice.infrastructure.drivenadapter.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("bootcamps")
public class BootcampEntity {
    @Id
    private Long id;
    private String name;
    private String description;
    @Column("launch_date")
    private LocalDate launchDate;
    @Column("duration_in_days")
    private Integer durationInDays;
}

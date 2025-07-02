package com.app.reactive_bootcamp_microservice.domain.model.pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CapabilityBasic {
    private Long id;
    private String name;
    private List<TechnologyBasic> technologies;
}

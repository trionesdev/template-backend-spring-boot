package com.trionesdev.backend.rest.backend.domains.masterdata.controller.ro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyCreateRO {
    private String code;
    private String name;
    private List<String> processCodes;
}

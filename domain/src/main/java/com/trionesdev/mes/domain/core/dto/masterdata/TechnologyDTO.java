package com.trionesdev.mes.domain.core.dto.masterdata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TechnologyDTO {
    private String id;
    private String code;
    private String name;
    private List<String> processCodes;
    private List<Process> processes;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Process {
        private String code;
        private String name;
    }
}

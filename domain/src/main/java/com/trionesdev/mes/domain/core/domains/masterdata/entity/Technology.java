package com.trionesdev.mes.domain.core.domains.masterdata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Technology {
    private String id;
    private String code;
    private String name;
    private List<Process> process;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Process {
        private String code;
        private String name;
    }

}

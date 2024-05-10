package com.trionesdev.mes.domain.core.domains.masterdata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessFlow {
    private String id;
    private String code;
    private String name;
    private List<Item> process;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private ItemType type;
        private String code;
        private BigDecimal ratio;
    }

    public enum ItemType {
        PROCESS, FLOW
    }

}

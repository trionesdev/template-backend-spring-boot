package com.trionesdev.mes.core.domains.masterdata.dto;

import com.trionesdev.mes.core.domains.masterdata.entity.ProcessFlow.ItemType;
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
public class ProcessFlowDTO {
    private String id;
    private String code;
    private String name;
    private List<Item> items;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private ItemType type;
        private String code;
        private BigDecimal ratio;
        private String name;
    }
}

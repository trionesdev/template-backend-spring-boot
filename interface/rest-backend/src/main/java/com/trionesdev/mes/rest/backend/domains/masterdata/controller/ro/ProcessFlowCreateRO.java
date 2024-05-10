package com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro;

import com.trionesdev.mes.domain.core.domains.masterdata.entity.ProcessFlow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessFlowCreateRO {
    private String code;
    @NotBlank
    private String name;
    private List<Item> items;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Item {
        private ProcessFlow.ItemType type;
        private String code;
        private BigDecimal ratio;
    }
}

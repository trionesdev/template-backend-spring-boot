package com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro;

import com.trionesdev.mes.core.domains.masterdata.internal.aggreate.entity.ProcessFlow;
import jakarta.validation.constraints.NotBlank;
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
public class ProcessFlowUpdateRO {
    @NotBlank
    private String name;
    private List<Item> items;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Item {
        private ProcessFlow.ItemType type;
        private String code;
        private BigDecimal ratio;
    }
}

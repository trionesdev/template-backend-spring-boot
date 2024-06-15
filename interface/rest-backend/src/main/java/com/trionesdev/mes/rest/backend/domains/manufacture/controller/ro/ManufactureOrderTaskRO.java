package com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.codehaus.commons.nullanalysis.NotNull;

import java.math.BigDecimal;
import java.time.Instant;

public class ManufactureOrderTaskRO {

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        @NotNull
        private BigDecimal ratio;
        @NotNull
        private BigDecimal planQuantity;
        private Instant planStartTime;
        private Instant planEndTime;
    }

}

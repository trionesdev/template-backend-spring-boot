package com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class ManufactureOrderTaskReportRO {

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Create {
        private String taskId;
        private String taskStatus;
        private String producerId; //生产者
        private BigDecimal goodQuantity; //良品数量
        private BigDecimal defectiveQuantity; //不良品数量
        private List<DefectiveItem> defectiveItems; //不良品项
        private Instant startTime;
        private Instant endTime;
    }


    @Data
    public static class DefectiveItem {
        private String code;
        private BigDecimal quantity;
    }
}

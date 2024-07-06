package com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro;

import com.trionesdev.mes.core.domains.manufacture.internal.enums.PricingMethod;
import com.trionesdev.mes.core.domains.manufacture.internal.enums.TaskStatus;
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
        private TaskStatus taskStatus;
        private String producerId; //生产者
        private BigDecimal goodQuantity; //良品数量
        private String unitId; //报工单位
        private BigDecimal defectiveQuantity; //不良品数量
        private List<DefectiveItem> defectiveItems; //不良品项
        private Instant startTime;
        private Instant endTime;
        private Integer duration;
        private PricingMethod pricingMethod;//计价方式
        private BigDecimal unitPrice; //单价
        private BigDecimal totalPrice; //总价工资
        private Boolean approved; //已审批
    }



    @Data
    public static class DefectiveItem {
        private String code;
        private BigDecimal quantity;
    }
}

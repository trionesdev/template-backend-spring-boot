package com.trionesdev.mes.core.domains.manufacture.internal.entity;

import com.trionesdev.commons.core.util.BigDecimalUtils;
import com.trionesdev.mes.core.domains.manufacture.internal.enums.PricingMethod;
import com.trionesdev.mes.core.domains.manufacture.internal.enums.TaskStatus;
import com.trionesdev.mes.infrastructure.ddd.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
public class ManufactureWorkReport implements AggregateRoot<String> {
    private String id;
    private String orderId;
    private String taskId;

    private String processCode; //任务工序名称
    private TaskStatus taskStatus;
    private String producerId; //生产者

    private String unitId; //报工单位
    private BigDecimal reportQuantity; //报工数量
    private BigDecimal goodQuantity; //良品数量
    private BigDecimal defectiveQuantity; //不良品数量
    private List<DefectiveItem> defectives; //不良品项
    private Instant startTime;
    private Instant endTime;
    private Integer duration;
    private PricingMethod pricingMethod;//计价方式
    private BigDecimal unitPrice; //单价
    private BigDecimal totalPrice; //总价工资
    private Boolean approved; //已审批
    private Instant reportTime;

    private Order order;


    public BigDecimal getReportQuantity() {
        return BigDecimalUtils.add(Optional.ofNullable(goodQuantity).orElse(BigDecimal.ZERO),Optional.ofNullable(defectiveQuantity).orElse(BigDecimal.ZERO));
    }

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Order {
        private String code;
    }

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DefectiveItem {
        private String code;
        private BigDecimal quantity;
    }

    public void createInitialize(){
        if (Objects.isNull(reportQuantity)){
            reportQuantity = BigDecimalUtils.add(goodQuantity, defectiveQuantity);
        }
       if (Objects.isNull(reportTime)){
           reportTime = Instant.now();
       }
    }

}

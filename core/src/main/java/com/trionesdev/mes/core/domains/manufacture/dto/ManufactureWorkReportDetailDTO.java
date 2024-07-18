package com.trionesdev.mes.core.domains.manufacture.dto;

import com.trionesdev.mes.core.domains.manufacture.internal.enums.PricingMethod;
import com.trionesdev.mes.core.domains.manufacture.internal.enums.TaskStatus;
import com.trionesdev.mes.core.domains.masterdata.dto.ProductDefinitionDTO;
import com.trionesdev.mes.core.domains.masterdata.dto.UnitDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;


@Data
public class ManufactureWorkReportDetailDTO {
    private String id;
    private String orderId;
    private String taskId;
    private TaskStatus taskStatus;
    private String processCode;
    private String producerId; //生产者
    private BigDecimal reportQuantity; //报工数量
    private BigDecimal goodQuantity; //良品数量
    private BigDecimal defectiveQuantity; //不良品数量
    private String unitId; //报工单位
    private List<DefectiveItem> defectives; //不良品项
    private Instant startTime;
    private Instant endTime;
    private Integer duration;
    private PricingMethod pricingMethod;//计价方式
    private BigDecimal unitPrice; //单价
    private BigDecimal totalPrice; //总价工资
    private Boolean approved; //已审批

    private Order order;
    private Task task;
    private ProductDefinitionDTO product;
    private Process process;
    private Producer producer;
    private UnitDTO unit;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Order{
        private String id;
        private String code;
        private String productCode;
    }

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Task {
        private String processCode; //工序名称
    }


    @Data
    public static class Process{
        private String code;
        private String name;
    }


    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Producer{
        private String id;
        private String nickname;
    }


    @Data
    public static class DefectiveItem {
        private String code;
        private String name;
        private BigDecimal quantity;
    }

}

package com.trionesdev.mes.core.domains.manufacture.dto;

import com.trionesdev.mes.core.domains.manufacture.internal.entity.ManufactureOrderTaskReport;
import com.trionesdev.mes.core.domains.manufacture.internal.enums.PricingMethod;
import com.trionesdev.mes.core.domains.manufacture.internal.enums.ReportApprovalStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
public class ManufactureWorkReportDetailDTO {
    private String id;
    private String orderId;
    private String taskId;

    private String taskProcessCode; //任务工序名称
    private String taskStatus;
    private String producerId; //生产者
    private BigDecimal reportQuantity; //报工数量
    private BigDecimal goodQuantity; //良品数量
    private BigDecimal defectiveQuantity; //不良品数量
    private List<ManufactureOrderTaskReport.DefectiveItem> defectiveItems; //不良品项

    private PricingMethod pricingMethod;//计价方式
    private Instant startTime;
    private Instant endTime;
    private BigDecimal quality;
    private BigDecimal unitPrice; //单价
    private BigDecimal totalPrice; //总价工资

    private Integer duration;//时长
    private ReportApprovalStatus approvalStatus;

    @Data
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

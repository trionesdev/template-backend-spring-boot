package com.trionesdev.mes.core.domains.manufacture.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.entity.BaseLogicEntity;
import com.trionesdev.mes.core.domains.manufacture.internal.enums.PricingMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "manufacture_work_report")
public class ManufactureWorkReportPO extends BaseLogicEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String orderId;
    private String taskId;

    private String processCode; //工序名称
    private String unitId;
    private BigDecimal reportQuantity; //报工数量
    private BigDecimal goodQuantity; //良品数量
    private BigDecimal defectiveQuantity; //不良品数量

    private Instant startTime;
    private Instant endTime;
    private Integer duration;
    private PricingMethod pricingMethod;//计价方式

    private BigDecimal unitPrice; //单价
    private BigDecimal totalPrice; //总价工资
    @TableField(value = "is_approved")
    private Boolean approved;
}

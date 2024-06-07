package com.trionesdev.mes.domain.core.domains.manufacture.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BaseLogicPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "manufacture_order_material")
public class ManufactureOrderMaterialPO extends BaseLogicPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String orderId;
    private String productCode;
    private String processCode;
    private BigDecimal unitUsage;
}

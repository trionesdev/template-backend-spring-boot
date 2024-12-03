package com.trionesdev.wms.core.domains.warehouse.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BaseLogicPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "warehouse_inbound_plan_item", autoResultMap = true)
public class WarehouseInboundPlanItemPO extends BaseLogicPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String warehouseInboundPlanId;
    private String supplierId;
    private String goodsId;
    private String unit;
    private Integer planInboundQuantity;
    private Integer actualInboundQuantity;
    private BigDecimal volume;
    private BigDecimal weight;
    private String remark;
}
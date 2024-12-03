package com.trionesdev.wms.core.domains.warehouse.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BaseLogicPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.enums.RelatedOrderSourceEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "warehouse_inbound_plan", autoResultMap = true)
public class WarehouseInboundPlanPO extends BaseLogicPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String sn;
    private String inboundType;
    private String supplierId;
    private Instant planInboundTime;
    private BigDecimal totalWeight;
    private BigDecimal totalVolume;
    private InboundPlanStatusEnum status;
    private String remark;
    private RelatedOrderSourceEnum relatedOrderSource;
    private String relatedOrderSn;

    public enum InboundPlanStatusEnum {
        PLANNING, CANCELLED, PART_INBOUND, FINISHED;
    }
}
package com.trionesdev.wms.core.domains.warehouse.dto;

import com.trionesdev.wms.core.domains.goods.dto.GoodsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;

@EqualsAndHashCode
@ToString
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseInboundPlanItemDTO {
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
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
    private GoodsDTO goods;
}
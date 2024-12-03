package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.vo;

import com.trionesdev.wms.core.domains.good.dto.GoodDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;

@Schema(title = "入库计划子项结果模型")
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class WarehouseInboundPlanItemVO {
    @Schema(title = "ID")
    private String id;
    @Schema(title = "入库计划ID")
    private String warehouseInboundPlanId;
    @Schema(title = "供应商ID")
    private String supplierId;
    @Schema(title = "货物ID")
    private String goodsId;
    @Schema(title = "单位")
    private String unit;
    @Schema(title = "计划入库数量")
    private Integer planInboundQuantity;
    @Schema(title = "实际入库数量")
    private Integer actualInboundQuantity;
    @Schema(title = "体积")
    private BigDecimal volume;
    @Schema(title = "重量")
    private BigDecimal weight;
    @Schema(title = "备注")
    private String remark;
    @Schema(title = "")
    private Instant createdAt;
    @Schema(title = "")
    private String createdBy;
    @Schema(title = "")
    private Instant updatedAt;
    @Schema(title = "")
    private String updatedBy;
    @Schema(title = "货品")
    private GoodDTO goods;
}

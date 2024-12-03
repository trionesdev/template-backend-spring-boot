package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Schema(title = "入库计划子项更新模型")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class WarehouseInboundPlanItemRO {
    @Schema(title = "ID")
    private String id;
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
}

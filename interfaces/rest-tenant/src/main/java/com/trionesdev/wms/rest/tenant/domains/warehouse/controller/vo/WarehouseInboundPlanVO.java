package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.vo;

import com.trionesdev.wms.core.domains.good.dto.GoodDTO;
import com.trionesdev.wms.core.domains.supplier.dto.SupplierDTO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseInboundPlanPO.InboundPlanStatusEnum;
import com.trionesdev.wms.core.domains.warehouse.dao.po.enums.RelatedOrderSourceEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Schema(title = "入库计划结果模型")
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class WarehouseInboundPlanVO {
    @Schema(title = "ID")
    private String id;
    @Schema(title = "单号")
    private String sn;
    @Schema(title = "入库类型")
    private String inboundType;
    @Schema(title = "供应商ID")
    private String supplierId;
    @Schema(title = "计划入库时间")
    private Instant planInboundTime;
    @Schema(title = "总重量")
    private BigDecimal totalWeight;
    @Schema(title = "总体积")
    private BigDecimal totalVolume;
    @Schema(title = "状态")
    private InboundPlanStatusEnum status;
    @Schema(title = "备注")
    private String remark;
    @Schema(title = "关联单据来源")
    private RelatedOrderSourceEnum relatedOrderSource;
    @Schema(title = "关联单据单号")
    private String relatedOrderSn;
    @Schema(title = "")
    private Instant createdAt;
    @Schema(title = "")
    private String createdBy;
    @Schema(title = "")
    private Instant updatedAt;
    @Schema(title = "")
    private String updatedBy;
    @Schema(title = "入库计划单子项")
    private List<WarehouseInboundPlanItemVO> items;
    @Schema(title = "供应商")
    private SupplierDTO supplier;
}

package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro;

import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseInboundPlanPO.InboundPlanStatusEnum;
import com.trionesdev.wms.core.domains.warehouse.dao.po.enums.RelatedOrderSourceEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Schema(title = "入库计划更新模型")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class WarehouseInboundPlanUpdateRO {
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
    @Schema(title = "备注")
    private String remark;
    @Schema(title = "关联单据来源")
    private RelatedOrderSourceEnum relatedOrderSource;
    @Schema(title = "关联单据单号")
    private String relatedOrderSn;
    @Schema(title = "状态")
    private InboundPlanStatusEnum status;
    @Schema(title = "入库计划子项")
    private List<WarehouseInboundPlanItemRO> items;
}

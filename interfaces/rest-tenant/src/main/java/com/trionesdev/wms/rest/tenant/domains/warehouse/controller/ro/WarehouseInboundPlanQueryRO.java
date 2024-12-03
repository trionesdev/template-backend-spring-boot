package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro;

import com.trionesdev.commons.core.page.PageCriteria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Schema(title = "入库计划查询模型")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseInboundPlanQueryRO extends PageCriteria {
    @Schema(title = "单号")
    private String sn;
}

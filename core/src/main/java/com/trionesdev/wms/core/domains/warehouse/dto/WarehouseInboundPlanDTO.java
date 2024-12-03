package com.trionesdev.wms.core.domains.warehouse.dto;

import com.trionesdev.wms.core.domains.supplier.dto.SupplierDTO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.WarehouseInboundPlanPO.InboundPlanStatusEnum;
import com.trionesdev.wms.core.domains.warehouse.dao.po.enums.RelatedOrderSourceEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@EqualsAndHashCode
@ToString
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseInboundPlanDTO {
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
    private Instant createdAt;
    private String createdBy;
    private Instant updatedAt;
    private String updatedBy;
    private List<WarehouseInboundPlanItemDTO> items;
    private SupplierDTO supplier;
}
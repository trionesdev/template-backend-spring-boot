package com.trionesdev.wms.core.domains.warehouse.dao.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseInboundPlanCriteria extends PageCriteria {
    private String sn;
}
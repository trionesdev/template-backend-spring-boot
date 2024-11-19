package com.trionesdev.wms.core.domains.warehouse.dao.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseContainerCriteria extends PageCriteria {
    private String code;
    private String name;
    private Boolean enabled;
    private String warehouseId;
}

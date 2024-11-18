package com.trionesdev.wms.core.domains.warehouse.dao.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import lombok.Data;

@Data
public class WarehouseLocationCriteria extends PageCriteria {
    private String code;
    private String name;
    private Boolean enabled;

    private String warehouseId;
    private String warehouseAreaId;
}

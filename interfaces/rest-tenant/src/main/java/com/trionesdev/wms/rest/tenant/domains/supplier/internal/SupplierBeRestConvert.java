package com.trionesdev.wms.rest.tenant.domains.supplier.internal;

import com.trionesdev.wms.core.domains.supplier.dao.criteria.SupplierCriteria;
import com.trionesdev.wms.core.domains.supplier.dao.po.SupplierPO;
import com.trionesdev.wms.rest.tenant.domains.supplier.controller.ro.SupplierQueryRO;
import com.trionesdev.wms.rest.tenant.domains.supplier.controller.ro.SupplierRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface SupplierBeRestConvert {
    SupplierCriteria from(SupplierQueryRO args);

    SupplierPO from(SupplierRO args);
}

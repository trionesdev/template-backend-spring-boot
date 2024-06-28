package com.trionesdev.mes.rest.backend.domains.supplier.internal;

import com.trionesdev.mes.core.domains.supplier.dao.criteria.SupplierCriteria;
import com.trionesdev.mes.core.domains.supplier.dao.po.SupplierPO;
import com.trionesdev.mes.rest.backend.domains.supplier.controller.query.SupplierQuery;
import com.trionesdev.mes.rest.backend.domains.supplier.controller.ro.SupplierCreateRO;
import com.trionesdev.mes.rest.backend.domains.supplier.controller.ro.SupplierUpdateRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
@Named("supplierBeRestBeanConvert")
public interface SupplierBeRestBeanConvert {

    SupplierPO from(SupplierCreateRO args);

    SupplierPO from(SupplierUpdateRO args);

    SupplierCriteria from(SupplierQuery query);
}

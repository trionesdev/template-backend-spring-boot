package com.trionesdev.wms.rest.tenant.domains.customer.internal;

import com.trionesdev.wms.core.domains.customer.dao.criteria.CustomerCriteria;
import com.trionesdev.wms.core.domains.customer.dao.po.CustomerPO;
import com.trionesdev.wms.rest.tenant.domains.customer.controller.ro.CustomerQueryRO;
import com.trionesdev.wms.rest.tenant.domains.customer.controller.ro.CustomerRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface CustomerBeRestConvert {
    CustomerCriteria from(CustomerQueryRO args);

    CustomerPO from(CustomerRO args);
}

package com.trionesdev.mes.rest.backend.domains.customer.internal;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import com.trionesdev.mes.domain.core.domains.customer.repository.criteria.CustomerCriteria;
import com.trionesdev.mes.domain.core.domains.customer.repository.po.CustomerPO;
import com.trionesdev.mes.rest.backend.domains.customer.controller.query.CustomerQuery;
import com.trionesdev.mes.rest.backend.domains.customer.controller.ro.CustomerCreateRO;
import com.trionesdev.mes.rest.backend.domains.customer.controller.ro.CustomerUpdateRO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
@Named("customerBeRestBeanConvert")
public interface CustomerBeRestBeanConvert {

    CustomerPO from(CustomerCreateRO args);

    CustomerPO from(CustomerUpdateRO args);

    CustomerCriteria from(CustomerQuery query);
}

package com.trionesdev.mes.domain.core.domains.customer.internal;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import com.trionesdev.mes.domain.core.domains.customer.repository.po.CustomerPO;
import com.trionesdev.mes.domain.core.dto.customer.CustomerDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
@Named("customerBeanConvert")
public interface CustomerBeanConvert {
    
    CustomerDTO poToDto(CustomerPO po);

}

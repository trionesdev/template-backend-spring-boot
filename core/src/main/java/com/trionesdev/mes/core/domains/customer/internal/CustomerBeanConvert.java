package com.trionesdev.mes.core.domains.customer.internal;

import com.trionesdev.mes.core.domains.customer.dao.po.CustomerPO;
import com.trionesdev.mes.core.domains.customer.dto.CustomerDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
@Named("customerBeanConvert")
public interface CustomerBeanConvert {
    
    CustomerDTO poToDto(CustomerPO po);

}

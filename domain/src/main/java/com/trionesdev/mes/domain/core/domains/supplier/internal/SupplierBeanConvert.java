package com.trionesdev.mes.domain.core.domains.supplier.internal;

import com.trionesdev.mes.domain.core.domains.supplier.repository.po.SupplierPO;
import com.trionesdev.mes.domain.core.dto.supplier.SupplierDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
@Named("supplierBeanConvert")
public interface SupplierBeanConvert {

    SupplierDTO poToDto(SupplierPO po);

}

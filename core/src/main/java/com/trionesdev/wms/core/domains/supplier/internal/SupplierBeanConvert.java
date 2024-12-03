package com.trionesdev.wms.core.domains.supplier.internal;

import com.trionesdev.wms.core.domains.supplier.dao.po.SupplierPO;
import com.trionesdev.wms.core.domains.supplier.dto.SupplierDTO;
import com.trionesdev.wms.core.domains.user.dao.po.UserPO;
import com.trionesdev.wms.core.domains.user.dto.UserBindCmd;
import com.trionesdev.wms.core.domains.user.dto.UserCreateCmd;
import com.trionesdev.wms.core.domains.user.dto.UserDTO;
import com.trionesdev.wms.core.domains.user.internal.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("supplierBeanConvert")
public interface SupplierBeanConvert {

    List<SupplierDTO> from(List<SupplierPO> args);
}

package com.trionesdev.mes.domain.core.domains.perm.internal;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("permBeanConvert")
public interface PermBeanConvert {
}

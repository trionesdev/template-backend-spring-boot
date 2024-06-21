package com.trionesdev.mes.domain.core.domains.tenant.internal;

import com.trionesdev.mes.domain.core.domains.tenant.entity.TenantMember;
import com.trionesdev.mes.domain.core.domains.tenant.repository.po.TenantMemberPO;
import com.trionesdev.mes.domain.core.domains.tenant.repository.po.TenantPO;
import com.trionesdev.mes.domain.core.dto.tenant.TenantDTO;
import com.trionesdev.mes.domain.core.dto.tenant.TenantMemberDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("tenantBeanConvert")
public interface TenantBeanConvert {
    TenantDTO tenantPoToDto(TenantPO tenantPO);

    TenantMemberPO entityToPO(TenantMember record);

    TenantMemberDTO entityToDTO(TenantMember record);

    TenantMemberDTO memberPOToDTO(TenantMemberPO record);
}

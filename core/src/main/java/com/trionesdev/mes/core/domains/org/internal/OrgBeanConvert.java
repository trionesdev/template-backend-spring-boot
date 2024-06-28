package com.trionesdev.mes.core.domains.org.internal;

import com.trionesdev.mes.core.domains.org.dao.po.DepartmentMemberPO;
import com.trionesdev.mes.core.domains.org.dao.po.DepartmentPO;
import com.trionesdev.mes.core.domains.org.dao.po.TenantMemberPO;
import com.trionesdev.mes.core.domains.org.dao.po.TenantPO;
import com.trionesdev.mes.core.domains.org.dto.DepartmentDTO;
import com.trionesdev.mes.core.domains.org.dto.DepartmentMemberDTO;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberCreateCmd;
import com.trionesdev.mes.core.domains.org.dto.TenantDTO;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("orgBeanConvert")
public interface OrgBeanConvert {

    //region tenant
    TenantMemberPO from(TenantMemberCreateCmd cmd);

    TenantMemberPO from(TenantMemberDTO tenantMember);

    TenantDTO tenantPoToDto(TenantPO tenantPO);

    TenantMemberDTO memberPOToDTO(TenantMemberPO record);
    //endregion

    //region department
    DepartmentDTO poToDto(DepartmentPO departmentPO);

    DepartmentMemberDTO poToDto(DepartmentMemberPO departmentMemberPO);
    //endregion
}

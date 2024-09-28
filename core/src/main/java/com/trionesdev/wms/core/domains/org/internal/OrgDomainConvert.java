package com.trionesdev.wms.core.domains.org.internal;

import com.trionesdev.wms.core.domains.org.dao.criteria.TenantMemberCriteria;
import com.trionesdev.wms.core.domains.org.dao.po.DepartmentMemberPO;
import com.trionesdev.wms.core.domains.org.dao.po.DepartmentPO;
import com.trionesdev.wms.core.domains.org.dao.po.TenantMemberPO;
import com.trionesdev.wms.core.domains.org.dao.po.TenantPO;
import com.trionesdev.wms.core.domains.org.dto.*;
import com.trionesdev.wms.core.domains.org.dto.TenantMemberDetailDTO;
import com.trionesdev.wms.core.domains.org.internal.aggreate.entity.TenantMember;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("orgBeanConvert")
public interface OrgDomainConvert {

    //region tenant
    TenantMemberPO from(TenantMemberCreateCmd cmd);

    TenantMember memberCreateCmdToEntity(TenantMemberCreateCmd cmd);

    TenantMemberPO from(TenantMemberDetailDTO tenantMember);

    TenantDTO tenantPoToDto(TenantPO tenantPO);

    TenantMemberDetailDTO memberPOToDTO(TenantMemberPO record);

    TenantMemberPO memberEntityToPo(TenantMember tenantMember);
    TenantMember memberPoToEntity(TenantMemberPO tenantMember);

    TenantMemberDTO memberEntityToDTO(TenantMember record);

    TenantMemberCriteria tenantMemberQueryToCriteria(TenantMemberQuery query);
    //endregion

    //region department
    DepartmentDTO poToDto(DepartmentPO departmentPO);

    DepartmentMemberDTO poToDto(DepartmentMemberPO departmentMemberPO);
    //endregion
}

package com.trionesdev.mes.domain.core.provider.ssp.org.impl;

import com.trionesdev.mes.domain.core.domains.org.service.DepartmentDomainService;
import com.trionesdev.mes.domain.core.dto.org.SetMemberDepartmentsArg;
import com.trionesdev.mes.domain.core.provider.ssp.org.OrgProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrgProviderLocal implements OrgProvider {
    private final DepartmentDomainService departmentService;

    @Override
    public void setMemberDepartments(SetMemberDepartmentsArg arg) {
        departmentService.setMemberDepartments(arg);
    }
}

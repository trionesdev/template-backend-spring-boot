package com.trionesdev.template.domains.org;

import com.trionesdev.template.BaseTest;
import com.trionesdev.template.core.domains.org.dao.criteria.TenantMemberCriteria;
import com.trionesdev.template.core.domains.org.service.impl.TenantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TenantServiceTest extends BaseTest {

    @Autowired
    private TenantService tenantService;

    @Test
    public void query_members_test() {
        TenantMemberCriteria criteria = TenantMemberCriteria.builder()
                .orGroup(TenantMemberCriteria.OrGroup.builder()
                        .ids(List.of("1"))
                        .departmentIds(List.of("1804363582654414849"))
                        .build())
                .build();
        var res = tenantService.findTenantMembers(criteria);
        System.out.println(res);
    }

}

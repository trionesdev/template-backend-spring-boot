package com.trionesdev.mes.domain.core.domains.tenant.repository.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TenantMemberCriteria extends PageCriteria {
    private String userId;
}

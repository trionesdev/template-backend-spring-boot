package com.trionesdev.mes.core.domains.org.dao.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TenantMemberCriteria extends PageCriteria {
    private String userId;
}

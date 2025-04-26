package com.trionesdev.template.core.domains.tenant.dao.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TenantCriteria extends PageCriteria {
    private String name;
}

package com.trionesdev.wms.core.domains.org.dao.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TenantCriteria extends PageCriteria {
    private String name;
}

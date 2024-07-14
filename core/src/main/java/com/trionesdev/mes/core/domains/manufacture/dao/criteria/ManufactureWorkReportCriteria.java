package com.trionesdev.mes.core.domains.manufacture.dao.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ManufactureWorkReportCriteria extends PageCriteria {
    private String orderId;
}

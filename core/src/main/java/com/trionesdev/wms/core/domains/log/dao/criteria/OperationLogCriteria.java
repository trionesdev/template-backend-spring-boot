package com.trionesdev.wms.core.domains.log.dao.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class OperationLogCriteria extends PageCriteria {
    private String type;
    private String category;
    private String action;
}

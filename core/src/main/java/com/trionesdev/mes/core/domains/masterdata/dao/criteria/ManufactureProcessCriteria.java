package com.trionesdev.mes.core.domains.masterdata.dao.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Collection;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ManufactureProcessCriteria  extends PageCriteria {
    private String code;
    private Collection<String> flowIds;
}

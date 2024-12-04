package com.trionesdev.wms.core.domains.goods.dao.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MeasureUnitCriteria extends PageCriteria {
    private String code;
    private String name;
    private Boolean enabled;
    private List<String> codes;
}

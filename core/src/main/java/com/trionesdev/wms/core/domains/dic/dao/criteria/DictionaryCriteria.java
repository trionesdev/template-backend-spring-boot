package com.trionesdev.wms.core.domains.dic.dao.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import com.trionesdev.wms.core.domains.dic.internal.enums.DictionaryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryCriteria extends PageCriteria {
    private DictionaryType type;
    private String typeCode;
}

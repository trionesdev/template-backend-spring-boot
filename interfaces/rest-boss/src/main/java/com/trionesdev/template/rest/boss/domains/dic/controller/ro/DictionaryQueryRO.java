package com.trionesdev.template.rest.boss.domains.dic.controller.ro;

import com.trionesdev.template.core.domains.dic.internal.enums.DictionaryType;
import lombok.Data;

@Data
public class DictionaryQueryRO {
    private DictionaryType type;
    private String typeCode;
}

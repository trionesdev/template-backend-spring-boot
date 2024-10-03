package com.trionesdev.wms.rest.boss.domains.dic.controller.ro;

import com.trionesdev.wms.core.domains.dic.internal.enums.DictionaryType;
import lombok.Data;

@Data
public class DictionaryQueryRO {
    private DictionaryType type;
    private String typeCode;
}

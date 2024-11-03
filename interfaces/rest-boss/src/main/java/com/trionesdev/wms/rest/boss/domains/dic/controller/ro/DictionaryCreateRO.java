package com.trionesdev.wms.rest.boss.domains.dic.controller.ro;

import com.trionesdev.wms.core.domains.dic.internal.enums.DictionaryType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DictionaryCreateRO {
    @NotNull
    private DictionaryType type;
    private String typeCode;
    private String name;
    private String code;
}

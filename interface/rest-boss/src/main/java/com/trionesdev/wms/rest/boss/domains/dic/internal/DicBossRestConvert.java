package com.trionesdev.wms.rest.boss.domains.dic.internal;

import com.trionesdev.wms.core.domains.dic.dao.po.DictionaryPO;
import com.trionesdev.wms.rest.boss.domains.dic.controller.ro.DictionaryCreateRO;
import com.trionesdev.wms.rest.boss.domains.dic.controller.ro.DictionaryUpdateRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface DicBossRestConvert {
    DictionaryPO from(DictionaryCreateRO args);

    DictionaryPO from(DictionaryUpdateRO args);
}

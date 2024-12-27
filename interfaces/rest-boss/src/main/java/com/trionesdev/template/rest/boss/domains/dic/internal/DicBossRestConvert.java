package com.trionesdev.template.rest.boss.domains.dic.internal;

import com.trionesdev.template.core.domains.dic.dao.criteria.CountryCriteria;
import com.trionesdev.template.core.domains.dic.dao.criteria.DictionaryCriteria;
import com.trionesdev.template.core.domains.dic.dao.criteria.DistrictCriteria;
import com.trionesdev.template.core.domains.dic.dao.po.DictionaryPO;
import com.trionesdev.template.rest.boss.domains.dic.controller.ro.*;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface DicBossRestConvert {
    DictionaryPO from(DictionaryCreateRO args);

    DictionaryPO from(DictionaryUpdateRO args);

    DictionaryCriteria from(DictionaryQueryRO args);

    DistrictCriteria from(DistrictQueryRO args);

    CountryCriteria from(CountryQuery args);
}

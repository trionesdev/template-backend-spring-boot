package com.trionesdev.mes.domain.core.provider.ssp.masterdata;

import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.provider.ssp.masterdata.dto.QueryProductDefinitionsArg;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("masterDataProviderBeanConvert")
public interface MasterDataProviderBeanConvert {
    ProductDefinitionCriteria from(QueryProductDefinitionsArg arg);
}

package com.trionesdev.mes.rest.backend.domains.masterdata.internal;

import com.trionesdev.mes.rest.backend.domains.masterdata.controller.query.ManufactureProcessQuery;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.query.ProductDefinitionQuery;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro.*;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ManufactureProcessCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.ManufactureProcessPO;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.ProductDefinitionPO;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.UnitPO;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.Technology;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("masterDataBeRestBeanConvert")
public interface MasterDataBeRestBeanConvert {

    //region unit
    UnitPO from(UnitCreateRO args);
    UnitPO from(UnitUpdateRO args);
    //endregion

    //region production definition
    ProductDefinitionPO from(ProductDefinitionCreateRO args);

    ProductDefinitionPO from(ProductDefinitionUpdateRO args);

    ProductDefinitionCriteria from(ProductDefinitionQuery args);
    //endregion

    //region manufacture process
    ManufactureProcessPO from(ManufactureProcessCreateRO args);

    ManufactureProcessPO from(ManufactureProcessUpdateRO args);

    ManufactureProcessCriteria from(ManufactureProcessQuery args);
    //endregion

    //region technology
    Technology from(TechnologyCreateRO args);
    //endregion
}

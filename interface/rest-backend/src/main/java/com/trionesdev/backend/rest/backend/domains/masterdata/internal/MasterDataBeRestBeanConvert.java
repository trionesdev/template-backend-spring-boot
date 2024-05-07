package com.trionesdev.backend.rest.backend.domains.masterdata.internal;

import com.trionesdev.backend.rest.backend.domains.masterdata.controller.query.ManufactureProcessQuery;
import com.trionesdev.backend.rest.backend.domains.masterdata.controller.query.ProductDefinitionQuery;
import com.trionesdev.backend.rest.backend.domains.masterdata.controller.ro.ManufactureProcessCreateRO;
import com.trionesdev.backend.rest.backend.domains.masterdata.controller.ro.ManufactureProcessUpdateRO;
import com.trionesdev.backend.rest.backend.domains.masterdata.controller.ro.ProductDefinitionCreateRO;
import com.trionesdev.backend.rest.backend.domains.masterdata.controller.ro.ProductDefinitionUpdateRO;
import com.trionesdev.backend.rest.backend.domains.masterdata.controller.ro.TechnologyCreateRO;
import com.trionesdev.backend.rest.backend.domains.masterdata.controller.ro.UnitCreateRO;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ManufactureProcessCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.ManufactureProcess;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.ProductDefinition;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.Unit;
import com.trionesdev.mes.domain.core.dto.masterdata.TechnologyDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("masterDataBeRestBeanConvert")
public interface MasterDataBeRestBeanConvert {

    //region unit
    Unit from(UnitCreateRO args);
    //endregion

    //region production definition
    ProductDefinition from(ProductDefinitionCreateRO args);

    ProductDefinition from(ProductDefinitionUpdateRO args);

    ProductDefinitionCriteria from(ProductDefinitionQuery args);
    //endregion

    //region manufacture process
    ManufactureProcess from(ManufactureProcessCreateRO args);

    ManufactureProcess from(ManufactureProcessUpdateRO args);

    ManufactureProcessCriteria from(ManufactureProcessQuery args);
    //endregion

    //region technology
    TechnologyDTO from(TechnologyCreateRO args);
    //endregion
}

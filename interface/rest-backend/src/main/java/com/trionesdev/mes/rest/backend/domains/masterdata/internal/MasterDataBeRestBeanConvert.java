package com.trionesdev.mes.rest.backend.domains.masterdata.internal;

import com.trionesdev.mes.core.domains.masterdata.dto.ManufactureProcessDTO;
import com.trionesdev.mes.core.domains.masterdata.internal.aggreate.entity.ProductBom;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.DefectiveCriteria;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.ProcessFlowCriteria;
import com.trionesdev.mes.core.domains.masterdata.dao.po.DefectivePO;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.query.DefectiveQuery;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.query.ManufactureProcessQuery;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.query.ProcessFlowQuery;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.query.ProductDefinitionQuery;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro.*;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.ManufactureProcessCriteria;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.core.domains.masterdata.dao.po.ProductDefinitionPO;
import com.trionesdev.mes.core.domains.masterdata.dao.po.UnitPO;
import com.trionesdev.mes.core.domains.masterdata.internal.aggreate.entity.ProcessFlow;
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
    ManufactureProcessDTO from(ManufactureProcessRO.Create args);

    ManufactureProcessDTO from(ManufactureProcessRO.Update args);

    ManufactureProcessCriteria from(ManufactureProcessQuery args);
    //endregion

    //region technology
    ProcessFlow from(ProcessFlowCreateRO args);

    ProcessFlow from(ProcessFlowUpdateRO args);

    ProcessFlowCriteria from(ProcessFlowQuery args);
    //endregion

    //region defective
    DefectivePO from(DefectiveRO.Create args);

    DefectivePO from(DefectiveRO.Update args);

    DefectiveCriteria from(DefectiveQuery args);
    //endregion

    ProductBom from(ProductBomCreateRO args);

    ProductBom from(ProductBomUpdateRO args);
}

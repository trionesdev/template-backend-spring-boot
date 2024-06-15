package com.trionesdev.mes.rest.backend.domains.manufacture.internal;

import com.trionesdev.mes.domain.core.domains.manufacture.entity.ManufactureOrder;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.criteria.ManufactureOrderCriteria;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.criteria.ManufactureOrderTaskCriteria;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderTaskPO;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.query.ManufactureOrderQuery;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.query.ManufactureOrderTaskQuery;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro.ManufactureOrderRO;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro.ManufactureOrderTaskRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("manufactureBeBeanConvert")
public interface ManufactureBeBeanConvert {
    //region manufacture order
    ManufactureOrder from(ManufactureOrderRO.Create args);

    ManufactureOrder from(ManufactureOrderRO.Update args);

    ManufactureOrder.Task from(ManufactureOrderRO.Task args);

    ManufactureOrder.Material from(ManufactureOrderRO.Material args);

    ManufactureOrderCriteria from(ManufactureOrderQuery args);
    //endregion

    ManufactureOrderTaskCriteria from(ManufactureOrderTaskQuery args);

    ManufactureOrderTaskPO from(ManufactureOrderTaskRO.Update args);

}

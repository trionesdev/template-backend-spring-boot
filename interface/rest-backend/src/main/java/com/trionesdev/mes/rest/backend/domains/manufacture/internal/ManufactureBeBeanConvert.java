package com.trionesdev.mes.rest.backend.domains.manufacture.internal;

import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderTaskReportPO;
import com.trionesdev.mes.core.domains.manufacture.dto.ManufactureOrderTaskReportDTO;
import com.trionesdev.mes.core.domains.manufacture.internal.entity.ManufactureOrder;
import com.trionesdev.mes.core.domains.manufacture.dao.criteria.ManufactureOrderCriteria;
import com.trionesdev.mes.core.domains.manufacture.dao.criteria.ManufactureOrderTaskCriteria;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderTaskPO;
import com.trionesdev.mes.core.domains.manufacture.internal.entity.ManufactureOrderTaskReport;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.query.ManufactureOrderQuery;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.query.ManufactureOrderTaskQuery;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro.ManufactureOrderRO;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro.ManufactureOrderTaskRO;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro.ManufactureOrderTaskReportRO;
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

    ManufactureOrderTaskReportDTO from(ManufactureOrderTaskReportRO.Create args);
}

package com.trionesdev.wms.rest.tenant.domains.good.internal;

import com.trionesdev.wms.core.domains.good.dao.criteria.GoodCriteria;
import com.trionesdev.wms.core.domains.good.dao.criteria.MeasureUnitCriteria;
import com.trionesdev.wms.core.domains.good.dao.po.GoodPO;
import com.trionesdev.wms.core.domains.good.dao.po.MeasureUnitPO;
import com.trionesdev.wms.rest.tenant.domains.good.controller.ro.GoodQueryRO;
import com.trionesdev.wms.rest.tenant.domains.good.controller.ro.GoodRO;
import com.trionesdev.wms.rest.tenant.domains.good.controller.ro.MeasureUnitQueryRO;
import com.trionesdev.wms.rest.tenant.domains.good.controller.ro.MeasureUnitRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface GoodBeRestConvert {

    GoodCriteria from(GoodQueryRO args);

    GoodPO from(GoodRO args);

    MeasureUnitCriteria from(MeasureUnitQueryRO args);

    MeasureUnitPO from(MeasureUnitRO args);
}

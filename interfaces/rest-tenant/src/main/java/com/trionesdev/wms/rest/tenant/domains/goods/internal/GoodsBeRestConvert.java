package com.trionesdev.wms.rest.tenant.domains.goods.internal;

import com.trionesdev.wms.core.domains.goods.dao.criteria.GoodsCriteria;
import com.trionesdev.wms.core.domains.goods.dao.criteria.MeasureUnitCriteria;
import com.trionesdev.wms.core.domains.goods.dao.po.GoodsPO;
import com.trionesdev.wms.core.domains.goods.dao.po.MeasureUnitPO;
import com.trionesdev.wms.rest.tenant.domains.goods.controller.ro.GoodsQueryRO;
import com.trionesdev.wms.rest.tenant.domains.goods.controller.ro.GoodsRO;
import com.trionesdev.wms.rest.tenant.domains.goods.controller.ro.MeasureUnitQueryRO;
import com.trionesdev.wms.rest.tenant.domains.goods.controller.ro.MeasureUnitRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface GoodsBeRestConvert {

    GoodsCriteria from(GoodsQueryRO args);

    GoodsPO from(GoodsRO args);

    MeasureUnitCriteria from(MeasureUnitQueryRO args);

    MeasureUnitPO from(MeasureUnitRO args);
}

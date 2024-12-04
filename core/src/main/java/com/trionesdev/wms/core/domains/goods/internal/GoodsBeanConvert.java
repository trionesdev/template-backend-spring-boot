package com.trionesdev.wms.core.domains.goods.internal;

import com.trionesdev.wms.core.domains.goods.dao.po.GoodsPO;
import com.trionesdev.wms.core.domains.goods.dto.GoodsDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface GoodsBeanConvert {

    GoodsDTO poToDto(GoodsPO po);
}

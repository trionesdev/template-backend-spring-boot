package com.trionesdev.wms.core.domains.goods.provider;

import com.trionesdev.wms.core.domains.goods.dto.GoodsDTO;
import com.trionesdev.wms.core.domains.goods.service.impl.GoodsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GoodsProvider {
    private final GoodsService goodsService;

    public List<GoodsDTO> listById(List<String> ids) {
        return goodsService.listById(ids);
    }
}

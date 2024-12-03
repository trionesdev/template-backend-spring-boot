package com.trionesdev.wms.core.domains.good.provider;

import com.trionesdev.wms.core.domains.good.dto.GoodDTO;
import com.trionesdev.wms.core.domains.good.service.impl.GoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class GoodsProvider {
    private final GoodService goodService;

    public List<GoodDTO> listById(List<String> ids) {
        return goodService.listById(ids);
    }
}

package com.trionesdev.mes.domain.core.domains.manufacture.service.impl;

import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.manufacture.entity.ManufactureOrder;
import com.trionesdev.mes.domain.core.domains.manufacture.internal.ManufactureBeanConvert;
import com.trionesdev.mes.domain.core.domains.manufacture.manager.impl.ManufactureOrderManager;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.criteria.ManufactureOrderCriteria;
import com.trionesdev.mes.domain.core.dto.manufacture.ManufactureOrderDTO;
import com.trionesdev.mes.domain.core.provider.ssp.custom.impl.CustomProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ManufactureOrderService {
    private final ManufactureBeanConvert convert;
    private final ManufactureOrderManager manufactureOrderManager;
    private final CustomProvider customProvider;

    public void createManufactureOrder(ManufactureOrder order) {
        if (StrUtil.isBlank(order.getCode())) {
            order.setCode(customProvider.generateCode("MANUFACTURE_ORDER"));
        }
        manufactureOrderManager.create(order);
    }

    public void deleteManufactureOrder(String id) {
        manufactureOrderManager.deleteById(id);
    }

    public void updateManufactureOrderById(ManufactureOrder order) {
        manufactureOrderManager.updateById(order);
    }

    public Optional<ManufactureOrderDTO> findManufactureOrderById(String id) {
        return manufactureOrderManager.findById(id).map(convert::entityToDto);
    }


    public PageInfo<ManufactureOrderDTO> findManufactureOrderPage(ManufactureOrderCriteria criteria) {
        PageInfo<ManufactureOrder> page = manufactureOrderManager.findPage(criteria);
        return PageUtils.of(page, assembleBatch(page.getRows()));
    }

    private List<ManufactureOrderDTO> assembleBatch(List<ManufactureOrder> records) {
        return records.stream().map(convert::entityToDto).collect(Collectors.toList());
    }
}

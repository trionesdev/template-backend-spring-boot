package com.trionesdev.mes.domain.core.domains.manufacture.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.manufacture.entity.ManufactureOrder;
import com.trionesdev.mes.domain.core.domains.manufacture.internal.ManufactureBeanConvert;
import com.trionesdev.mes.domain.core.domains.manufacture.manager.impl.ManufactureOrderManager;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.criteria.ManufactureOrderCriteria;
import com.trionesdev.mes.domain.core.dto.manufacture.ManufactureOrderDTO;
import com.trionesdev.mes.domain.core.dto.masterdata.ProductDefinitionDTO;
import com.trionesdev.mes.domain.core.provider.ssp.custom.impl.CustomProvider;
import com.trionesdev.mes.domain.core.provider.ssp.masterdata.impl.MasterDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ManufactureOrderService {
    private final ManufactureBeanConvert convert;
    private final ManufactureOrderManager manufactureOrderManager;
    private final MasterDataProvider masterDataProvider;
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
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        Set<String> productCodes = records.stream().map(ManufactureOrder::getProductCode).collect(Collectors.toSet());
        Map<String, ProductDefinitionDTO> productsMap = masterDataProvider.getProductsByCodes(productCodes).stream().collect(Collectors.toMap(ProductDefinitionDTO::getCode, v -> v, (v1, v2) -> v1));

        return records.stream().map(order -> {
            var dto = convert.entityToDto(order);
            dto.setProduct(
                    Optional.ofNullable(productsMap.get(order.getProductCode())).map(product -> ManufactureOrderDTO.Product.builder().code(product.getCode()).name(product.getName()).build()).orElse(null)
            );
            return dto;
        }).collect(Collectors.toList());
    }
}

package com.trionesdev.wms.core.domains.good.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.wms.core.domains.good.dao.criteria.GoodCriteria;
import com.trionesdev.wms.core.domains.good.dao.criteria.MeasureUnitCriteria;
import com.trionesdev.wms.core.domains.good.dao.po.GoodPO;
import com.trionesdev.wms.core.domains.good.dao.po.MeasureUnitPO;
import com.trionesdev.wms.core.domains.good.dto.GoodDTO;
import com.trionesdev.wms.core.domains.good.internal.GoodBeanConvert;
import com.trionesdev.wms.core.domains.good.manager.impl.GoodManager;
import com.trionesdev.wms.core.domains.good.manager.impl.MeasureUnitManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GoodService {
    private final GoodManager goodManager;
    private final MeasureUnitManager measureUnitManager;
    private final GoodBeanConvert convert;

    public void create(GoodPO po) {
        goodManager.create(po);
    }

    public void updateById(GoodPO po) {
        goodManager.updateById(po);
    }

    public Optional<GoodDTO> findById(String id) {
        return goodManager.findById(id).map(this::assemble);
    }

    public List<GoodDTO> findList(GoodCriteria criteria) {
        List<GoodPO> list = goodManager.findList(criteria);
        return assembleBatch(list);
    }

    public PageInfo<GoodDTO> findPage(GoodCriteria criteria) {
        PageInfo<GoodPO> page = goodManager.findPage(criteria);
        return PageUtils.of(page, assembleBatch(page.getRows()));
    }

    public void deleteByIds(List<String> ids) {
        goodManager.deleteByIds(ids);
    }

    private GoodDTO assemble(GoodPO po) {
        GoodDTO dto = convert.poToDto(po);
        if (StrUtil.isNotBlank(dto.getUnitCode())) {
            Optional<MeasureUnitPO> measureUnitOptional = measureUnitManager.findByCode(dto.getUnitCode());
            measureUnitOptional.ifPresent(measureUnitPO -> dto.setMeasureUnit(measureUnitPO.getName()));
        }
        return dto;
    }

    public List<GoodDTO> assembleBatch(List<GoodPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        return records.stream().map(this::assemble).collect(Collectors.toList());
    }

    public List<GoodDTO> listById(List<String> ids) {
        List<GoodPO> list = goodManager.listById(ids);
        return assembleBatch(list);
    }
}

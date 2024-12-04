package com.trionesdev.wms.core.domains.goods.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.wms.core.domains.goods.dao.criteria.GoodsCriteria;
import com.trionesdev.wms.core.domains.goods.dao.po.GoodsPO;
import com.trionesdev.wms.core.domains.goods.dao.po.MeasureUnitPO;
import com.trionesdev.wms.core.domains.goods.dto.GoodsDTO;
import com.trionesdev.wms.core.domains.goods.internal.GoodsBeanConvert;
import com.trionesdev.wms.core.domains.goods.manager.impl.GoodsManager;
import com.trionesdev.wms.core.domains.goods.manager.impl.MeasureUnitManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class GoodsService {
    private final GoodsManager goodManager;
    private final MeasureUnitManager measureUnitManager;
    private final GoodsBeanConvert convert;

    public void create(GoodsPO po) {
        goodManager.create(po);
    }

    public void updateById(GoodsPO po) {
        goodManager.updateById(po);
    }

    public Optional<GoodsDTO> findById(String id) {
        return goodManager.findById(id).map(this::assemble);
    }

    public List<GoodsDTO> findList(GoodsCriteria criteria) {
        List<GoodsPO> list = goodManager.findList(criteria);
        return assembleBatch(list);
    }

    public PageInfo<GoodsDTO> findPage(GoodsCriteria criteria) {
        PageInfo<GoodsPO> page = goodManager.findPage(criteria);
        return PageUtils.of(page, assembleBatch(page.getRows()));
    }

    public void deleteByIds(List<String> ids) {
        goodManager.deleteByIds(ids);
    }

    private GoodsDTO assemble(GoodsPO po) {
        GoodsDTO dto = convert.poToDto(po);
        if (StrUtil.isNotBlank(dto.getUnitCode())) {
            Optional<MeasureUnitPO> measureUnitOptional = measureUnitManager.findByCode(dto.getUnitCode());
            measureUnitOptional.ifPresent(measureUnitPO -> dto.setMeasureUnit(measureUnitPO.getName()));
        }
        return dto;
    }

    public List<GoodsDTO> assembleBatch(List<GoodsPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        return records.stream().map(this::assemble).collect(Collectors.toList());
    }

    public List<GoodsDTO> listById(List<String> ids) {
        List<GoodsPO> list = goodManager.listById(ids);
        return assembleBatch(list);
    }
}

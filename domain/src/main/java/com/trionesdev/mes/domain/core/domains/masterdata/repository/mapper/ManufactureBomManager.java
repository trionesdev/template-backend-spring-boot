package com.trionesdev.mes.domain.core.domains.masterdata.repository.mapper;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ManufactureBom;
import com.trionesdev.mes.domain.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ManufactureBomCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.impl.ManufactureBomItemRepository;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.impl.ManufactureBomRepository;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ManufactureBomItemPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ManufactureBomPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ManufactureBomManager {
    private final MasterDataBeanConvert convert;
    private final ManufactureBomRepository manufactureBomRepository;
    private final ManufactureBomItemRepository manufactureBomItemRepository;

    @Transactional
    public void create(ManufactureBom bom) {
        ManufactureBomPO po = bom.toPo();
        manufactureBomRepository.save(po);
        if (CollectionUtil.isNotEmpty(bom.getMaterials())) {
            List<ManufactureBomItemPO> items = bom.getMaterials().stream().map(item -> {
                ManufactureBomItemPO itemPO = item.toPo();
                itemPO.setBomId(po.getId());
                return itemPO;
            }).collect(Collectors.toList());
            manufactureBomItemRepository.saveBatch(items);
        }
    }

    @Transactional
    public void deleteById(String id) {
        manufactureBomRepository.removeById(id);
        manufactureBomItemRepository.deleteByBomId(id);
    }

    @Transactional
    public void updateById(ManufactureBom bom) {
        manufactureBomItemRepository.deleteByBomId(bom.getId());
        manufactureBomRepository.updateById(bom.toPo());
        if (CollectionUtil.isNotEmpty(bom.getMaterials())) {
            List<ManufactureBomItemPO> items = bom.getMaterials().stream().map(item -> {
                ManufactureBomItemPO itemPO = item.toPo();
                itemPO.setBomId(bom.getId());
                return itemPO;
            }).collect(Collectors.toList());
            manufactureBomItemRepository.saveBatch(items);
        }
    }

    public Optional<ManufactureBom> findById(String id) {
        return Optional.ofNullable(manufactureBomRepository.getById(id)).map(convert::poToEntity)
                .map(t -> {
                    t.setMaterials(manufactureBomItemRepository.selectListByBomId(t.getId()).stream().map(convert::poToEntity).collect(Collectors.toList()));
                    return t;
                });
    }

    public List<ManufactureBom> findList(ManufactureBomCriteria criteria) {
        List<ManufactureBomPO> list = manufactureBomRepository.selectList(criteria);
        return assembleEntityBatch(list);
    }

    public PageInfo<ManufactureBom> findPage(ManufactureBomCriteria criteria) {
        PageInfo<ManufactureBomPO> page = manufactureBomRepository.selectPage(criteria);
        return PageUtils.of(page, assembleEntityBatch(page.getRows()));
    }


    public List<ManufactureBom> assembleEntityBatch(List<ManufactureBomPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        Set<String> bomIds = records.stream().map(ManufactureBomPO::getId).collect(Collectors.toSet());
        Map<String, List<ManufactureBomItemPO>> bomItemMap = manufactureBomItemRepository.selectListByBomIds(bomIds).stream().collect(Collectors.groupingBy(ManufactureBomItemPO::getBomId));
        return records.stream().map(convert::poToEntity).peek(t ->
                        t.setMaterials(bomItemMap.getOrDefault(t.getId(), Collections.emptyList()).stream().map(convert::poToEntity)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}

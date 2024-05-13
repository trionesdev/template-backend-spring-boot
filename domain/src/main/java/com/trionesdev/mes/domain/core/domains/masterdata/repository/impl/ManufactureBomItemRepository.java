package com.trionesdev.mes.domain.core.domains.masterdata.repository.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ManufactureBomItemPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.mapper.ManufactureBomItemMapper;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ManufactureBomPO;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public class ManufactureBomItemRepository extends ServiceImpl<ManufactureBomItemMapper, ManufactureBomItemPO> {

    public void deleteByBomId(String bomId) {
        baseMapper.delete(new LambdaUpdateWrapper<ManufactureBomItemPO>().eq(ManufactureBomItemPO::getBomId, bomId));
    }

    public List<ManufactureBomItemPO> selectListByBomId(String bomId) {
        return baseMapper.selectList(new LambdaUpdateWrapper<ManufactureBomItemPO>().eq(ManufactureBomItemPO::getBomId, bomId));
    }

    public List<ManufactureBomItemPO> selectListByBomIds(Collection<String> bomIds) {
        if (CollectionUtil.isEmpty(bomIds)) {
            return Collections.emptyList();
        }
        return baseMapper.selectList(new LambdaUpdateWrapper<ManufactureBomItemPO>().in(ManufactureBomItemPO::getBomId, bomIds));
    }

}

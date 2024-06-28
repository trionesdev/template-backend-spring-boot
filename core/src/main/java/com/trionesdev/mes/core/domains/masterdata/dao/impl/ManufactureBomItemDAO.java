package com.trionesdev.mes.core.domains.masterdata.dao.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.core.domains.masterdata.dao.po.ManufactureBomItemPO;
import com.trionesdev.mes.core.domains.masterdata.dao.mapper.ManufactureBomItemMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Repository
public class ManufactureBomItemDAO extends ServiceImpl<ManufactureBomItemMapper, ManufactureBomItemPO> {

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

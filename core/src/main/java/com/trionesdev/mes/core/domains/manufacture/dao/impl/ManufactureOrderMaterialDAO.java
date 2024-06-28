package com.trionesdev.mes.core.domains.manufacture.dao.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.core.domains.manufacture.dao.mapper.ManufactureOrderMaterialMapper;
import com.trionesdev.mes.core.domains.manufacture.dao.po.ManufactureOrderMaterialPO;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class ManufactureOrderMaterialDAO extends ServiceImpl<ManufactureOrderMaterialMapper, ManufactureOrderMaterialPO> {
    public void deleteByOrderId(String orderId) {
        baseMapper.delete(new LambdaUpdateWrapper<ManufactureOrderMaterialPO>().eq(ManufactureOrderMaterialPO::getOrderId, orderId));
    }

    public List<ManufactureOrderMaterialPO> selectListByOrderId(String orderId) {
        return baseMapper.selectList(new LambdaUpdateWrapper<ManufactureOrderMaterialPO>().eq(ManufactureOrderMaterialPO::getOrderId, orderId));
    }

    public List<ManufactureOrderMaterialPO> selectListByOrderIds(Collection<String> orderIds) {
        return baseMapper.selectList(new LambdaUpdateWrapper<ManufactureOrderMaterialPO>().in(ManufactureOrderMaterialPO::getOrderId, orderIds));
    }
}

package com.trionesdev.mes.domain.core.domains.manufacture.repository.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.mapper.ManufactureOrderMaterialMapper;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderMaterialPO;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class ManufactureOrderMaterialRepository extends ServiceImpl<ManufactureOrderMaterialMapper, ManufactureOrderMaterialPO> {
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

package com.trionesdev.mes.domain.core.domains.manufacture.repository.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.mapper.ManufactureOrderTaskMapper;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderTaskPO;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public class ManufactureOrderTaskRepository extends ServiceImpl<ManufactureOrderTaskMapper, ManufactureOrderTaskPO> {

    public void deleteByOrderId(String orderId) {
        baseMapper.delete(new LambdaUpdateWrapper<ManufactureOrderTaskPO>().eq(ManufactureOrderTaskPO::getOrderId, orderId));
    }

    public List<ManufactureOrderTaskPO> selectListByOrderId(String orderId) {
        return baseMapper.selectList(new LambdaUpdateWrapper<ManufactureOrderTaskPO>().eq(ManufactureOrderTaskPO::getOrderId, orderId));
    }

    public List<ManufactureOrderTaskPO> selectListByOrderIds(Collection<String> orderIds) {
        return baseMapper.selectList(new LambdaUpdateWrapper<ManufactureOrderTaskPO>().in(ManufactureOrderTaskPO::getOrderId, orderIds));
    }
}

package com.trionesdev.mes.domain.core.domains.manufacture.repository.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.criteria.ManufactureOrderTaskCriteria;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.mapper.ManufactureOrderTaskMapper;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderTaskPO;
import org.checkerframework.checker.units.qual.C;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class ManufactureOrderTaskRepository extends ServiceImpl<ManufactureOrderTaskMapper, ManufactureOrderTaskPO> {

    private LambdaQueryWrapper<ManufactureOrderTaskPO> buildQueryWrapper(ManufactureOrderTaskCriteria criteria) {
        LambdaQueryWrapper<ManufactureOrderTaskPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.exists(StrUtil.isNotBlank(criteria.getOrderCode()), "    select * from manufacture_order order \n" +
                            "             where order.is_deleted=false\n" +
                            "             and order.id=task.order_id\n" +
                            "             and order.code='" + criteria.getOrderCode() + "'")
                    .eq(StrUtil.isNotBlank(criteria.getProcessCode()), ManufactureOrderTaskPO::getProcessCode, criteria.getProcessCode())
            ;
        }
        return queryWrapper;
    }

    public void deleteByOrderId(String orderId) {
        baseMapper.delete(new LambdaUpdateWrapper<ManufactureOrderTaskPO>().eq(ManufactureOrderTaskPO::getOrderId, orderId));
    }

    public List<ManufactureOrderTaskPO> selectListByOrderId(String orderId) {
        return baseMapper.selectList(new LambdaUpdateWrapper<ManufactureOrderTaskPO>().eq(ManufactureOrderTaskPO::getOrderId, orderId));
    }

    public List<ManufactureOrderTaskPO> selectListByOrderIds(Collection<String> orderIds) {
        return baseMapper.selectList(new LambdaUpdateWrapper<ManufactureOrderTaskPO>().in(ManufactureOrderTaskPO::getOrderId, orderIds));
    }

    public PageInfo<ManufactureOrderTaskPO> selectPage(ManufactureOrderTaskCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

}

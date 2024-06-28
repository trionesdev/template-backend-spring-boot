package com.trionesdev.mes.core.domains.masterdata.dao.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.core.domains.masterdata.dao.mapper.ManufactureProcessMapper;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.ManufactureProcessCriteria;
import com.trionesdev.mes.core.domains.masterdata.dao.po.ManufactureProcessPO;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class ManufactureProcessDAO extends ServiceImpl<ManufactureProcessMapper, ManufactureProcessPO> {

    private LambdaQueryWrapper<ManufactureProcessPO> buildQueryWrapper(ManufactureProcessCriteria criteria) {
        LambdaQueryWrapper<ManufactureProcessPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), ManufactureProcessPO::getCode, criteria.getCode())
                    .exists(CollectionUtil.isNotEmpty(criteria.getFlowIds()), "     select * from master_data_process_flow_item\n" +
                            "             where master_data_process_flow_item.is_deleted=false\n" +
                            "             and    master_data_manufacture_process.code=master_data_process_flow_item.code\n" +
                            "             and master_data_process_flow_item.flow_id in ('" + StrUtil.join(",", criteria.getFlowIds()) + "') ");

        }
        return queryWrapper;
    }

    public List<ManufactureProcessPO> selectList(ManufactureProcessCriteria criteria) {
        return baseMapper.selectList(buildQueryWrapper(criteria));
    }

    public PageInfo<ManufactureProcessPO> selectPage(ManufactureProcessCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

    public ManufactureProcessPO selectByCode(String code) {
        return baseMapper.selectOne(new LambdaQueryWrapper<ManufactureProcessPO>().eq(ManufactureProcessPO::getCode, code).last(" limit 1 "));
    }

    public List<ManufactureProcessPO> selectListByCodes(Collection<String> codes) {
        return baseMapper.selectList(new LambdaQueryWrapper<ManufactureProcessPO>().in(ManufactureProcessPO::getCode, codes));
    }

}

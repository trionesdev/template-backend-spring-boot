package com.trionesdev.mes.domain.core.domains.masterdata.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ManufactureProcessCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.ManufactureProcessPO;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.mapper.ManufactureProcessMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class ManufactureProcessDAO extends ServiceImpl<ManufactureProcessMapper, ManufactureProcessPO> {

    private LambdaQueryWrapper<ManufactureProcessPO> buildQueryWrapper(ManufactureProcessCriteria criteria) {
        LambdaQueryWrapper<ManufactureProcessPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), ManufactureProcessPO::getCode, criteria.getCode());
        }
        return queryWrapper;
    }

    public List<ManufactureProcessPO> selectList(ManufactureProcessCriteria criteria) {
        return baseMapper.selectList(buildQueryWrapper(criteria));
    }

    public PageInfo<ManufactureProcessPO> selectPage(ManufactureProcessCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

    public List<ManufactureProcessPO> selectListByCodes(Collection<String> codes) {
        return baseMapper.selectList(new LambdaQueryWrapper<ManufactureProcessPO>().in(ManufactureProcessPO::getCode, codes));
    }

}

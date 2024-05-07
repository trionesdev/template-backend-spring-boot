package com.trionesdev.mes.domain.core.domains.masterdata.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ManufactureProcessCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.ManufactureProcess;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.mapper.ManufactureProcessMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class ManufactureProcessDAO extends ServiceImpl<ManufactureProcessMapper, ManufactureProcess> {

    private LambdaQueryWrapper<ManufactureProcess> buildQueryWrapper(ManufactureProcessCriteria criteria) {
        LambdaQueryWrapper<ManufactureProcess> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(criteria.getCode()), ManufactureProcess::getCode, criteria.getCode());
        }
        return queryWrapper;
    }

    public List<ManufactureProcess> selectList(ManufactureProcessCriteria criteria) {
        return baseMapper.selectList(buildQueryWrapper(criteria));
    }

    public PageInfo<ManufactureProcess> selectPage(ManufactureProcessCriteria criteria) {
        return MpPageUtils.of(baseMapper.selectPage(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

    public List<ManufactureProcess> selectListByCodes(Collection<String> codes) {
        return baseMapper.selectList(new LambdaQueryWrapper<ManufactureProcess>().in(ManufactureProcess::getCode, codes));
    }

}

package com.trionesdev.wms.core.domains.dic.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.dic.dao.criteria.CountryCriteria;
import com.trionesdev.wms.core.domains.dic.dao.mapper.CountryMapper;
import com.trionesdev.wms.core.domains.dic.dao.po.CountryPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class CountryDAO extends ServiceImpl<CountryMapper, CountryPO> {
    private LambdaQueryWrapper<CountryPO> buildQueryWrapper(final CountryCriteria criteria) {
        LambdaQueryWrapper<CountryPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper
                    .like(StringUtils.isNoneBlank(criteria.getName()), CountryPO::getName, criteria.getName())
                    .eq(StringUtils.isNoneBlank(criteria.getCode()), CountryPO::getCode, criteria.getCode());
        }
        return queryWrapper;
    }

    public List<CountryPO> selectList(final CountryCriteria criteria) {
        return list(buildQueryWrapper(criteria));
    }

}

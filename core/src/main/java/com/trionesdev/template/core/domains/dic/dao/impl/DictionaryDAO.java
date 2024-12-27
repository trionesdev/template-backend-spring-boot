package com.trionesdev.template.core.domains.dic.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.template.core.domains.dic.dao.criteria.DictionaryCriteria;
import com.trionesdev.template.core.domains.dic.dao.mapper.DictionaryMapper;
import com.trionesdev.template.core.domains.dic.dao.po.DictionaryPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class DictionaryDAO extends ServiceImpl<DictionaryMapper, DictionaryPO> {

    private LambdaQueryWrapper<DictionaryPO> buildQueryWrapper(DictionaryCriteria criteria) {
        LambdaQueryWrapper<DictionaryPO> queryWrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(criteria)) {
            queryWrapper
                    .eq(Objects.nonNull(criteria.getType()), DictionaryPO::getType, criteria.getType())
                    .eq(StringUtils.isNoneBlank(criteria.getTypeCode()), DictionaryPO::getTypeCode, criteria.getTypeCode())
            ;
        }
        return queryWrapper;
    }

    public List<DictionaryPO> selectListByTypeCode(String typeCode) {
        return lambdaQuery().eq(DictionaryPO::getTypeCode, typeCode).list();
    }

    public List<DictionaryPO> selectList(DictionaryCriteria criteria) {
        return baseMapper.selectList(buildQueryWrapper(criteria));
    }

    public PageInfo<DictionaryPO> selectPage(DictionaryCriteria criteria) {
        return MpPageUtils.of(page(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

}

package com.trionesdev.template.core.domains.log.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.template.core.domains.log.dao.criteria.OperationLogCriteria;
import com.trionesdev.template.core.domains.log.dao.mapper.OperationLogMapper;
import com.trionesdev.template.core.domains.log.dao.po.OperationLogPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class OperationLogDAO extends ServiceImpl<OperationLogMapper, OperationLogPO> {
    private LambdaQueryWrapper<OperationLogPO> buildQueryWrapper(OperationLogCriteria criteria) {
        var queryWrapper = new LambdaQueryWrapper<OperationLogPO>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StringUtils.isNotBlank(criteria.getType()), OperationLogPO::getType, criteria.getType())
                    .eq(StringUtils.isNotBlank(criteria.getCategory()), OperationLogPO::getCategory, criteria.getCategory())
                    .eq(StringUtils.isNotBlank(criteria.getAction()), OperationLogPO::getAction, criteria.getAction());
        }
        return queryWrapper;
    }

    public PageInfo<OperationLogPO> selectPage(OperationLogCriteria criteria) {
        return MpPageUtils.of(
                page(MpPageUtils.page(criteria), buildQueryWrapper(criteria))
        );
    }

}

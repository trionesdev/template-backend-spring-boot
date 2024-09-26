package com.trionesdev.wms.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.perm.dao.criteria.FunctionalResourceCriteria;
import com.trionesdev.wms.core.domains.perm.dao.mapper.FunctionalResourceMapper;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourcePO;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class FunctionalResourceDAO extends ServiceImpl<FunctionalResourceMapper, FunctionalResourcePO> {

    private LambdaQueryWrapper<FunctionalResourcePO> buildQueryWrapper(FunctionalResourceCriteria criteria) {
        var queryWrapper = new LambdaQueryWrapper<FunctionalResourcePO>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StringUtils.isNotBlank(criteria.getAppCode()), FunctionalResourcePO::getAppCode, criteria.getAppCode())
                    .eq(Objects.nonNull(criteria.getClientType()), FunctionalResourcePO::getClientType, criteria.getClientType())
                    .eq(StringUtils.isNotBlank(criteria.getParentId()), FunctionalResourcePO::getParentId, criteria.getParentId())
                    .eq(StringUtils.isNotBlank(criteria.getGroupCode()), FunctionalResourcePO::getGroupCode, criteria.getGroupCode());
        }
        return queryWrapper;
    }

    public List<FunctionalResourcePO> selectListByParentId(String parentId) {
        return lambdaQuery().eq(FunctionalResourcePO::getParentId, parentId).list();
    }

    public List<FunctionalResourcePO> selectList(FunctionalResourceCriteria criteria) {
        return baseMapper.selectList(buildQueryWrapper(criteria));
    }

    public List<FunctionalResourcePO> selectListByClientType(String appCode, ClientType clientType) {
        return lambdaQuery().eq(StringUtils.isNoneBlank(appCode), FunctionalResourcePO::getAppCode, appCode).eq(FunctionalResourcePO::getClientType, clientType).list();
    }

}

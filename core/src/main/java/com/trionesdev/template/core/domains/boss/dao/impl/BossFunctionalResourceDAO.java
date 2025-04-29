package com.trionesdev.template.core.domains.boss.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.template.core.domains.boss.dao.criteria.BossFunctionalResourceCriteria;
import com.trionesdev.template.core.domains.boss.dao.mapper.BossFunctionalResourceMapper;
import com.trionesdev.template.core.domains.boss.dao.po.BossFunctionalResourcePO;
import com.trionesdev.template.core.domains.boss.shared.enums.ClientType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class BossFunctionalResourceDAO extends ServiceImpl<BossFunctionalResourceMapper, BossFunctionalResourcePO> {

    private LambdaQueryWrapper<BossFunctionalResourcePO> buildQueryWrapper(BossFunctionalResourceCriteria criteria) {
        var queryWrapper = new LambdaQueryWrapper<BossFunctionalResourcePO>();
        if (Objects.nonNull(criteria)) {
            queryWrapper
                    .eq(Objects.nonNull(criteria.getClientType()), BossFunctionalResourcePO::getClientType, criteria.getClientType())
                    .eq(StringUtils.isNotBlank(criteria.getParentId()), BossFunctionalResourcePO::getParentId, criteria.getParentId())
                    .eq(StringUtils.isNotBlank(criteria.getGroupCode()), BossFunctionalResourcePO::getGroupCode, criteria.getGroupCode());
        }
        return queryWrapper;
    }

    public List<BossFunctionalResourcePO> selectListByParentId(String parentId) {
        return lambdaQuery().eq(BossFunctionalResourcePO::getParentId, parentId).list();
    }

    public List<BossFunctionalResourcePO> selectList(BossFunctionalResourceCriteria criteria) {
        return baseMapper.selectList(buildQueryWrapper(criteria));
    }

    public List<BossFunctionalResourcePO> selectListByAppClient(ClientType clientType) {
        return lambdaQuery()
                .eq(Objects.nonNull(clientType), BossFunctionalResourcePO::getClientType, clientType).list();
    }

    public void deleteByAppClient(ClientType clientType) {
        lambdaUpdate()
                .eq(Objects.nonNull(clientType), BossFunctionalResourcePO::getClientType, clientType).remove();
    }

}

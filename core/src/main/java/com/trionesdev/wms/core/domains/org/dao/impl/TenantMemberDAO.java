package com.trionesdev.wms.core.domains.org.dao.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.org.dao.criteria.TenantMemberCriteria;
import com.trionesdev.wms.core.domains.org.dao.po.TenantMemberPO;
import com.trionesdev.wms.core.domains.org.dao.mapper.TenantMemberMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class TenantMemberDAO extends ServiceImpl<TenantMemberMapper, TenantMemberPO> {
    private final ActorContext actorContext;

    private LambdaQueryWrapper<TenantMemberPO> buildQueryWrapper(TenantMemberCriteria criteria) {
        var queryWrapper = new LambdaQueryWrapper<TenantMemberPO>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StrUtil.isNotBlank(actorContext.getTenantId()), TenantMemberPO::getTenantId, actorContext.getTenantId())
                    .and(StringUtils.isNoneBlank(criteria.getWd()), wdQueryWrapper -> {
                        wdQueryWrapper.like(TenantMemberPO::getName, criteria.getWd())
                                .or().like(TenantMemberPO::getNickname, criteria.getWd());
                    })
                    .and(Objects.nonNull(criteria.getOrGroup()), orQueryWrapper -> {
                        orQueryWrapper.in(CollectionUtil.isNotEmpty(criteria.getOrGroup().getIds()), TenantMemberPO::getId, criteria.getOrGroup().getIds())
                                .or(CollectionUtil.isNotEmpty(criteria.getOrGroup().getDepartmentIds()), departmentIdsQueryWrapper -> {
                                    departmentIdsQueryWrapper.exists(CollectionUtil.isNotEmpty(criteria.getOrGroup().getDepartmentIds()), "select * from org_department_member dm " +
                                            "where dm.is_deleted = false  " +
                                            "and department_id in (" + StrUtil.join(",", criteria.getOrGroup().getDepartmentIds().stream().map(departmentId -> "'" + departmentId + "'").collect(Collectors.toSet())) + ") " +
                                            "and dm.user_id = org_tenant_member.user_id");
                                });
                    });
        }
        return queryWrapper;
    }

    public TenantMemberPO selectByUserId(String userId) {
        return lambdaQuery().eq(TenantMemberPO::getUserId, userId)
                .eq(StrUtil.isNotBlank(actorContext.getTenantId()), TenantMemberPO::getUserId, userId)
                .last(" limit 1 ").one();
    }

    public List<TenantMemberPO> selectListByUserIds(Collection<String> userIds) {
        if (CollectionUtil.isEmpty(userIds)) {
            return Collections.emptyList();
        }
        return lambdaQuery().in(TenantMemberPO::getUserId, userIds).list();
    }

    public List<TenantMemberPO> selectList(TenantMemberCriteria criteria) {
        return list(buildQueryWrapper(criteria).orderByDesc(TenantMemberPO::getCreatedAt));
    }

    public PageInfo<TenantMemberPO> selectPage(TenantMemberCriteria criteria) {
        return MpPageUtils.of(
                page(MpPageUtils.page(criteria), buildQueryWrapper(criteria).orderByDesc(TenantMemberPO::getCreatedAt))
        );
    }

    public TenantMemberPO selectByUsername(String tenantId, String username) {
        return lambdaQuery().eq(StringUtils.isNoneBlank(tenantId), TenantMemberPO::getTenantId, tenantId)
                .eq(TenantMemberPO::getUsername, username)
                .last(" limit 1 ").one();
    }

    public TenantMemberPO selectByPhone(String tenantId, String phone) {
        return lambdaQuery().eq(StringUtils.isNoneBlank(tenantId), TenantMemberPO::getTenantId, tenantId)
                .eq(TenantMemberPO::getPhone, phone)
                .last(" limit 1 ").one();
    }

    public TenantMemberPO selectByEmail(String tenantId, String email) {
        return lambdaQuery().eq(StringUtils.isNoneBlank(tenantId), TenantMemberPO::getTenantId, tenantId)
                .eq(TenantMemberPO::getEmail, email)
                .last(" limit 1 ").one();
    }

    public void updateByUserId(TenantMemberPO tenantMemberPO) {
        Objects.requireNonNull(tenantMemberPO.getUserId());
        lambdaUpdate().eq(TenantMemberPO::getUserId, tenantMemberPO.getUserId()).setEntity(tenantMemberPO).update();
    }

}

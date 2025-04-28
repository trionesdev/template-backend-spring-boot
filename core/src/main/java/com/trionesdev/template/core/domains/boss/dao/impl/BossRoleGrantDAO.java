package com.trionesdev.template.core.domains.boss.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.template.core.domains.boss.dao.criteria.BossRoleGrantCriteria;
import com.trionesdev.template.core.domains.boss.dao.mapper.BossRoleGrantMapper;
import com.trionesdev.template.core.domains.boss.dao.po.BossRoleGrantPO;
import com.trionesdev.template.core.domains.boss.shared.enums.RoleSubjectType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class BossRoleGrantDAO extends ServiceImpl<BossRoleGrantMapper, BossRoleGrantPO> {

    private LambdaQueryWrapper<BossRoleGrantPO> buildQueryWrapper(final BossRoleGrantCriteria criteria) {
        var queryWrapper = new LambdaQueryWrapper<BossRoleGrantPO>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StringUtils.isNoneBlank(criteria.getRoleId()), BossRoleGrantPO::getRoleId, criteria.getRoleId())
                    .eq(Objects.nonNull(criteria.getSubjectType()), BossRoleGrantPO::getSubjectType, criteria.getSubjectType())
            ;
        }
        return queryWrapper;
    }

    public List<BossRoleGrantPO> selectListByObj(RoleSubjectType grantObjType, String grantObjId) {
        return lambdaQuery().eq(BossRoleGrantPO::getSubjectType, grantObjType).eq(BossRoleGrantPO::getSubject, grantObjId).list();
    }

    public BossRoleGrantPO selectUnique(String roleId, RoleSubjectType grantObjType, String grantObjId) {
        return lambdaQuery().eq(BossRoleGrantPO::getRoleId, roleId)
                .eq(BossRoleGrantPO::getSubjectType, grantObjType)
                .eq(BossRoleGrantPO::getSubject, grantObjId).last(" limit 1 ")
                .one();
    }

    public PageInfo<BossRoleGrantPO> selectPage(BossRoleGrantCriteria criteria) {
        return MpPageUtils.of(page(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

    public void removeRoleGrantBySubjects(String roleId, RoleSubjectType grantObjType, List<String> subjects) {
        if (CollectionUtils.isEmpty(subjects)) {
            return;
        }
        lambdaUpdate().eq(BossRoleGrantPO::getRoleId, roleId).eq(BossRoleGrantPO::getSubjectType, grantObjType)
                .in(BossRoleGrantPO::getSubject, subjects).remove();
    }

}

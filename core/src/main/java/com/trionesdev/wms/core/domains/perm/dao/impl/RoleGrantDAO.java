package com.trionesdev.wms.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.perm.dao.criteria.RoleGrantCriteria;
import com.trionesdev.wms.core.domains.perm.dao.po.RoleGrantPO;
import com.trionesdev.wms.core.domains.perm.dao.mapper.RoleGrantMapper;
import com.trionesdev.wms.core.domains.perm.internal.enums.RoleSubjectType;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class RoleGrantDAO extends ServiceImpl<RoleGrantMapper, RoleGrantPO> {

    private LambdaQueryWrapper<RoleGrantPO> buildQueryWrapper(final RoleGrantCriteria criteria) {
        var queryWrapper = new LambdaQueryWrapper<RoleGrantPO>();
        if (Objects.nonNull(criteria)) {
            queryWrapper.eq(StringUtils.isNoneBlank(criteria.getRoleId()), RoleGrantPO::getRoleId, criteria.getRoleId())
                    .eq(Objects.nonNull(criteria.getSubjectType()), RoleGrantPO::getSubjectType, criteria.getSubjectType())
            ;
        }
        return queryWrapper;
    }

    public List<RoleGrantPO> selectListByObj(RoleSubjectType grantObjType, String grantObjId) {
        return lambdaQuery().eq(RoleGrantPO::getSubjectType, grantObjType).eq(RoleGrantPO::getSubject, grantObjId).list();
    }

    public RoleGrantPO selectUnique(String roleId, RoleSubjectType grantObjType, String grantObjId) {
        return lambdaQuery().eq(RoleGrantPO::getRoleId, roleId)
                .eq(RoleGrantPO::getSubjectType, grantObjType)
                .eq(RoleGrantPO::getSubject, grantObjId).last(" limit 1 ")
                .one();
    }

    public PageInfo<RoleGrantPO> selectPage(RoleGrantCriteria criteria) {
        return MpPageUtils.of(page(MpPageUtils.page(criteria), buildQueryWrapper(criteria)));
    }

    public void removeRoleGrantByObjs(String roleId, RoleSubjectType grantObjType, List<String> grantObjIds) {
        if (CollectionUtils.isEmpty(grantObjIds)) {
            return;
        }
        lambdaUpdate().eq(RoleGrantPO::getRoleId, roleId).eq(RoleGrantPO::getSubjectType, grantObjType)
                .in(RoleGrantPO::getSubject, grantObjIds).remove();
    }
}

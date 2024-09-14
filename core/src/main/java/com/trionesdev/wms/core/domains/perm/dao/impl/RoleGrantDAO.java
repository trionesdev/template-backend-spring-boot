package com.trionesdev.wms.core.domains.perm.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.perm.dao.po.RoleGrantPO;
import com.trionesdev.wms.core.domains.perm.dao.mapper.RoleGrantMapper;
import com.trionesdev.wms.core.domains.perm.internal.enums.RoleGrantObjType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleGrantDAO extends ServiceImpl<RoleGrantMapper, RoleGrantPO> {
    public List<RoleGrantPO> selectListByObj(RoleGrantObjType grantObjType, String grantObjId) {
        return lambdaQuery().eq(RoleGrantPO::getGrantObjType, grantObjType).eq(RoleGrantPO::getGrantObjId, grantObjId).list();
    }
}

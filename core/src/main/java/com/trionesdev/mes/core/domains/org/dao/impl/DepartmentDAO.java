package com.trionesdev.mes.core.domains.org.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.core.domains.org.dao.mapper.DepartmentMapper;
import com.trionesdev.mes.core.domains.org.dao.po.DepartmentPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDAO extends ServiceImpl<DepartmentMapper, DepartmentPO> {

    public List<DepartmentPO> selectListByParentId(String parentId) {
        return lambdaQuery().eq(DepartmentPO::getParentId, parentId).list();
    }

}

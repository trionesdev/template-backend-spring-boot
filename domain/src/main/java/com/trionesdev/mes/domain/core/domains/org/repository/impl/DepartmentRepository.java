package com.trionesdev.mes.domain.core.domains.org.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.org.repository.mapper.DepartmentMapper;
import com.trionesdev.mes.domain.core.domains.org.repository.po.DepartmentPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository extends ServiceImpl<DepartmentMapper, DepartmentPO> {

    public List<DepartmentPO> selectListByParentId(String parentId) {
        return lambdaQuery().eq(DepartmentPO::getParentId, parentId).list();
    }

}

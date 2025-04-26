package com.trionesdev.template.core.domains.boss.service.impl;

import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.template.core.domains.boss.dao.po.BossDepartmentPO;
import com.trionesdev.template.core.domains.boss.internal.BossDomainConvert;
import com.trionesdev.template.core.domains.boss.manager.impl.BossDepartmentManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BossDepartmentService {
    private final ActorContext actorContext;
    private final BossDomainConvert convert;
    private final BossDepartmentManager departmentManager;



    public void createDepartment(BossDepartmentPO department) {
        departmentManager.createDepartment(department);
    }

    public void deleteDepartmentById(String id) {
        departmentManager.deleteDepartmentById(id);
    }

    public void updateDepartmentById(BossDepartmentPO department) {
        departmentManager.updateDepartmentById(department);
    }

    public Optional<BossDepartmentPO> findDepartmentById(String id) {
        return departmentManager.findDepartmentById(id);
    }

}

package com.trionesdev.wms.core.domains.perm.service.impl;

import com.trionesdev.wms.core.domains.perm.dao.po.RolePO;
import com.trionesdev.wms.core.domains.perm.manager.impl.RoleManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleService {
    private final RoleManager roleManager;

    public void create(RolePO role) {
        roleManager.create(role);
    }

    public void deleteById(String id) {
        roleManager.deleteById(id);
    }

    public void updateById(RolePO role) {
        roleManager.updateById(role);
    }

    public Optional<RolePO> findById(String id) {
        return roleManager.findById(id);
    }

}

package com.trionesdev.wms.core.domains.perm.manager.impl;

import com.trionesdev.wms.core.domains.perm.dao.po.RolePO;
import com.trionesdev.wms.core.domains.perm.dao.impl.RoleDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleManager {
    private final RoleDAO roleRepository;

    public void create(RolePO record) {
        roleRepository.save(record);
    }

    public void deleteById(String id) {
        roleRepository.removeById(id);
    }

    public void updateById(RolePO record) {
        roleRepository.updateById(record);
    }

    public Optional<RolePO> findById(String id) {
        return Optional.ofNullable(roleRepository.getById(id));
    }


}

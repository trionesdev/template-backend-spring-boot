package com.trionesdev.mes.domain.core.domains.perm.manager.impl;

import com.trionesdev.mes.domain.core.domains.perm.repository.impl.RoleRepository;
import com.trionesdev.mes.domain.core.domains.perm.repository.po.RolePO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleManager {
    private final RoleRepository roleRepository;

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

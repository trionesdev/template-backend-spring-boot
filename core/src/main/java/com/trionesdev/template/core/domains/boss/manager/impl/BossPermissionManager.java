package com.trionesdev.template.core.domains.boss.manager.impl;

import com.trionesdev.template.core.domains.boss.dao.impl.BossPermissionDAO;
import com.trionesdev.template.core.domains.boss.dao.po.BossPermissionPO;
import com.trionesdev.template.core.domains.boss.internal.BossPermDomainConvert;
import com.trionesdev.template.core.domains.boss.shared.enums.ClientType;
import com.trionesdev.template.core.domains.boss.shared.enums.PermissionSubjectType;
import com.trionesdev.template.core.domains.boss.shared.model.BossPermissionResource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BossPermissionManager {
    private final BossPermDomainConvert convert;
    private final BossPermissionDAO permissionDAO;


    @Transactional
    public void saveSubjectPermissions(ClientType clientType, PermissionSubjectType subjectType, String subjectId, Set<BossPermissionPO> permissions) {
        permissionDAO.deleteBySubject(clientType, subjectType, subjectId);
        if (CollectionUtils.isEmpty(permissions)) {
            return;
        }
        permissions.forEach(permissionPO -> {
            permissionPO.setClientType(clientType);
            permissionPO.setSubjectType(subjectType);
            permissionPO.setSubject(subjectId);
        });
        permissionDAO.saveBatch(permissions);
    }

    public Set<BossPermissionResource> findPermissionsBySubject(ClientType clientType, PermissionSubjectType subjectType, String subject) {
        return permissionDAO.selectListBySubject(clientType, subjectType, subject).stream().map(permissionPO -> {
            return convert.permissionPoToPermissionResource(permissionPO);
        }).collect(Collectors.toSet());
    }

    public Set<BossPermissionResource> findPermissionsBySubjects(ClientType clientType, PermissionSubjectType subjectType, Collection<String> subjects) {
        return permissionDAO.selectResourcesBySubjects(clientType, subjectType, subjects).stream().map(permissionPO -> {
            return convert.permissionPoToPermissionResource(permissionPO);
        }).collect(Collectors.toSet());
    }

}

package com.trionesdev.template.core.domains.perm.manager.impl;

import com.trionesdev.template.core.domains.perm.dao.impl.PermissionDAO;
import com.trionesdev.template.core.domains.perm.dao.po.PermissionPO;
import com.trionesdev.template.core.domains.perm.internal.PermDomainConvert;
import com.trionesdev.template.core.domains.perm.shared.enums.ClientType;
import com.trionesdev.template.core.domains.perm.shared.enums.PermissionSubjectType;
import com.trionesdev.template.core.domains.perm.shared.model.PermissionResource;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PermissionManager {
    private final PermDomainConvert convert;
    private final PermissionDAO permissionDAO;


    @Transactional
    public void saveSubjectPermissions(String appCode, ClientType clientType, PermissionSubjectType subjectType, String subjectId, Set<PermissionPO> permissions) {
        permissionDAO.deleteBySubject(appCode, clientType, subjectType, subjectId);
        if (CollectionUtils.isEmpty(permissions)) {
            return;
        }
        permissions.forEach(permissionPO -> {
            permissionPO.setAppCode(appCode);
            permissionPO.setClientType(clientType);
            permissionPO.setSubjectType(subjectType);
            permissionPO.setSubject(subjectId);
        });
        permissionDAO.saveBatch(permissions);
    }

    public Set<PermissionResource> findPermissionsBySubject(String appCode, ClientType clientType, PermissionSubjectType subjectType, String subject) {
        return permissionDAO.selectListBySubject(appCode, clientType, subjectType, subject).stream().map(permissionPO -> {
            return convert.permissionPoToPermissionResource(permissionPO);
        }).collect(Collectors.toSet());
    }

    public Set<PermissionResource> findPermissionsBySubjects(String appCode, ClientType clientType, PermissionSubjectType subjectType, Collection<String> subjects) {
        return permissionDAO.selectResourcesBySubjects(appCode, clientType, subjectType, subjects).stream().map(permissionPO -> {
            return convert.permissionPoToPermissionResource(permissionPO);
        }).collect(Collectors.toSet());
    }

}

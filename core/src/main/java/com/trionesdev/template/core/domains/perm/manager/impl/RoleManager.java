package com.trionesdev.template.core.domains.perm.manager.impl;

import com.google.common.collect.Lists;
import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.template.core.domains.perm.dao.criteria.RoleCriteria;
import com.trionesdev.template.core.domains.perm.dao.criteria.RoleGrantCriteria;
import com.trionesdev.template.core.domains.perm.dao.impl.RoleGrantDAO;
import com.trionesdev.template.core.domains.perm.dao.po.RoleGrantPO;
import com.trionesdev.template.core.domains.perm.dao.po.RolePO;
import com.trionesdev.template.core.domains.perm.dao.impl.RoleDAO;
import com.trionesdev.template.core.domains.perm.internal.enums.RoleSubjectType;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoleManager {
    private final RoleDAO roleDAO;
    private final RoleGrantDAO roleGrantDAO;

    public void create(RolePO record) {
        if (Objects.equals(IdentityConstants.STRING_ID_ZERO_VALUE, record.getParentId())) {
            record.setPrevIds(List.of(IdentityConstants.STRING_ID_ZERO_VALUE));
        } else {
            var parentPrevIds = roleDAO.getById(record.getParentId()).getPrevIds();
            record.setPrevIds(ListUtils.union(parentPrevIds, List.of(record.getParentId())));
        }
        roleDAO.save(record);
    }

    public void deleteById(String id) {
        var subRoles = roleDAO.selectSubList(id);
        if (CollectionUtils.isNotEmpty(subRoles)) {
            throw new BusinessException("CAN_NOT_DELETE_ROLE_HAS_SUB");
        }
        roleDAO.removeById(id);
    }

    public void updateById(RolePO record) {
        if (Objects.equals(record.getId(), record.getParentId())) {
            throw new BusinessException("ROLE_SELF_PARENT");
        }
        var roleSnap = roleDAO.getById(record.getId());
        if (Objects.nonNull(record.getParentId()) && !Objects.equals(record.getParentId(), roleSnap.getParentId())) {
            if (Objects.equals(IdentityConstants.STRING_ID_ZERO_VALUE, record.getParentId())) {
                record.setPrevIds(List.of(IdentityConstants.STRING_ID_ZERO_VALUE));
            } else {
                var parentPrevIds = roleDAO.getById(record.getParentId()).getPrevIds();
                record.setPrevIds(ListUtils.union(parentPrevIds, List.of(record.getParentId())));
            }
        }
        roleDAO.updateById(record);
    }

    public Optional<RolePO> findById(String id) {
        return Optional.ofNullable(roleDAO.getById(id));
    }

    public List<RolePO> findList(RoleCriteria criteria) {
        return roleDAO.selectList(criteria);
    }

    /**
     * 查询所有父级角色
     *
     * @param id
     * @return
     */
    public List<RolePO> findPrevRoles(String id) {
        var role = roleDAO.getById(id);
        if (!Objects.equals(IdentityConstants.STRING_ID_ZERO_VALUE, role.getParentId())) {
            return roleDAO.listByIds(role.getPrevIds());
        } else {
            return Collections.emptyList();
        }
    }

    /**
     * 根据角色id获取角色及所有父角色
     *
     * @param id
     * @return
     */
    public List<RolePO> findRelationRolesById(String id) {
        var roles = new ArrayList<RolePO>();
        var role = roleDAO.getById(id);
        if (!Objects.equals(IdentityConstants.STRING_ID_ZERO_VALUE, role.getParentId())) {
            var prevRoles = roleDAO.listByIds(role.getPrevIds());
            roles.addAll(prevRoles);
        }
        roles.add(role);
        return roles;
    }

    /**
     * 查询对象关联的角色（包含父级角色）
     *
     * @param grantObjType
     * @param grantObjId
     * @return
     */
    public Set<RolePO> findObjRelationRoles(RoleSubjectType grantObjType, String grantObjId) {
        var roleGrants = roleGrantDAO.selectListByObj(grantObjType, grantObjId);
        if (CollectionUtils.isEmpty(roleGrants)) {
            return Collections.emptySet();
        }
        var allRoles = new HashSet<RolePO>();
        var roleIds = roleGrants.stream().map(RoleGrantPO::getRoleId).collect(Collectors.toSet());
        var roles = roleDAO.listByIds(roleIds);
        Set<String> prevIds = roles.stream().map(role -> {
            return CollectionUtils.isNotEmpty(role.getPrevIds()) ? role.getPrevIds() : new ArrayList<String>();
        }).flatMap(List::stream).collect(Collectors.toSet());
        var prevRoles = roleDAO.listByIds(prevIds);
        allRoles.addAll(roles);
        allRoles.addAll(prevRoles);
        return allRoles;
    }

    @Transactional
    public void roleGrant(String roleId, RoleSubjectType subjectType, List<String> subjects) {
        if (CollectionUtils.isEmpty(subjects)) {
            return;
        }
        List<RoleGrantPO> grants = Lists.newArrayList();
        subjects.forEach(grantObjId -> {
            var roleGrant = roleGrantDAO.selectUnique(roleId, subjectType, grantObjId);
            if (Objects.isNull(roleGrant)) {
                grants.add(RoleGrantPO.builder().roleId(roleId).subjectType(subjectType).subject(grantObjId).build());
            }
        });
        roleGrantDAO.saveBatch(grants);
    }

    public void removeRoleGrantByObjs(String roleId, RoleSubjectType grantObjType, List<String> grantObjIds) {
        roleGrantDAO.removeRoleGrantByObjs(roleId, grantObjType, grantObjIds);
    }

    public PageInfo<RoleGrantPO> findRoleGrantPage(RoleGrantCriteria criteria) {
        return roleGrantDAO.selectPage(criteria);
    }


}

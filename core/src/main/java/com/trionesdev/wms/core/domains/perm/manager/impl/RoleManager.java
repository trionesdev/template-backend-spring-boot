package com.trionesdev.wms.core.domains.perm.manager.impl;

import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.wms.core.domains.perm.dao.criteria.RoleCriteria;
import com.trionesdev.wms.core.domains.perm.dao.po.RolePO;
import com.trionesdev.wms.core.domains.perm.dao.impl.RoleDAO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleManager {
    private final RoleDAO roleDAO;

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
    public List<RolePO> findWithPrev(String id) {
        var roles = new ArrayList<RolePO>();
        var role = roleDAO.getById(id);
        if (!Objects.equals(IdentityConstants.STRING_ID_ZERO_VALUE, role.getParentId())) {
            var prevRoles = roleDAO.listByIds(role.getPrevIds());
            roles.addAll(prevRoles);
        }
        roles.add(role);
        return roles;
    }

}

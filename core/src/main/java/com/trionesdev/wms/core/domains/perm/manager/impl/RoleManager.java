package com.trionesdev.wms.core.domains.perm.manager.impl;

import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.wms.core.domains.perm.dao.criteria.RoleCriteria;
import com.trionesdev.wms.core.domains.perm.dao.po.RolePO;
import com.trionesdev.wms.core.domains.perm.dao.impl.RoleDAO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.ListUtils;
import org.springframework.stereotype.Service;

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
            record.setPrevIds(ListUtils.union(roleDAO.getById(record.getParentId()).getPrevIds(), List.of(record.getParentId())));
        }
        roleDAO.save(record);
    }

    public void deleteById(String id) {
        roleDAO.removeById(id);
    }

    public void updateById(RolePO record) {
        roleDAO.updateById(record);
    }

    public Optional<RolePO> findById(String id) {
        return Optional.ofNullable(roleDAO.getById(id));
    }

    public List<RolePO> findList(RoleCriteria criteria) {
        return roleDAO.selectList(criteria);
    }

}

package com.trionesdev.template.core.domains.org.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.template.core.domains.org.dao.criteria.DepartmentCriteria;
import com.trionesdev.template.core.domains.org.dao.criteria.DepartmentMemberCriteria;
import com.trionesdev.template.core.domains.org.dao.impl.DepartmentDAO;
import com.trionesdev.template.core.domains.org.dao.impl.DepartmentMemberDAO;
import com.trionesdev.template.core.domains.org.dao.po.DepartmentMemberPO;
import com.trionesdev.template.core.domains.org.dao.po.DepartmentPO;
import com.trionesdev.template.core.domains.org.internal.aggreate.entity.TenantMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class DepartmentManager {
    private final DepartmentDAO departmentDAO;
    private final DepartmentMemberDAO departmentMemberDAO;

    //region department

    public List<String> findPrevIds(String parentId) {
        if (Objects.equals(IdentityConstants.STRING_ID_ZERO_VALUE, parentId)) {
            return Collections.emptyList();
        }
        return Optional.ofNullable(departmentDAO.getById(parentId)).map(t -> {
            List<String> paths = new ArrayList<>(t.getPrevIds());
            paths.add(t.getId());
            return paths;
        }).orElse(Collections.emptyList());
    }

    public void createDepartment(DepartmentPO department) {
        department.setPrevIds(findPrevIds(department.getParentId()));
        departmentDAO.save(department);
    }

    public void deleteDepartmentById(String id) {
        departmentDAO.removeById(id);
    }

    public void updateDepartmentById(DepartmentPO department) {
        if (Objects.equals(department.getId(), department.getParentId())) {
            throw new BusinessException("DEPARTMENT_SELF_PARENT");
        }
        department.setPrevIds(findPrevIds(department.getParentId()));
        departmentDAO.updateById(department);
    }

    public Optional<DepartmentPO> findDepartmentById(String id) {
        return Optional.ofNullable(departmentDAO.getById(id));
    }

    public List<DepartmentPO> findDepartments() {
        return departmentDAO.list();
    }

    public List<DepartmentPO> findDepartments(DepartmentCriteria criteria) {
        return departmentDAO.selectList(criteria);
    }

    public List<DepartmentPO> findDepartmentsByParentId(String parentId) {
        return departmentDAO.selectListByParentId(parentId);
    }

    public List<DepartmentPO> findDepartmentsByIds(Collection<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return departmentDAO.listByIds(ids);
    }

    //endregion

    //region department member
    public void createDepartmentMember(DepartmentMemberPO departmentMember) {
        departmentMemberDAO.save(departmentMember);
    }

    public void deleteDepartmentMemberById(String id) {
        departmentMemberDAO.removeById(id);
    }

    public void updateDepartmentMemberById(DepartmentMemberPO departmentMember) {
        departmentMemberDAO.updateById(departmentMember);
    }

    public void setMemberDepartments(TenantMember tenantMember, List<String> departmentIds) {
        Objects.requireNonNull(tenantMember.getUserId());
        departmentMemberDAO.deleteByUserId(tenantMember.getUserId());
        if (CollectionUtil.isEmpty(departmentIds)) {
            return;
        }
        List<DepartmentMemberPO> departmentMembers = departmentIds.stream().map(departmentId -> {
            return DepartmentMemberPO.builder().userId(tenantMember.getUserId()).departmentId(departmentId).build();
        }).collect(Collectors.toList());
        departmentMemberDAO.saveBatch(departmentMembers);
    }

    public Optional<DepartmentMemberPO> findDepartmentMemberById(String id) {
        return Optional.ofNullable(departmentMemberDAO.getById(id));
    }

    public List<DepartmentMemberPO> findDepartmentMembers(DepartmentMemberCriteria criteria) {
        return departmentMemberDAO.selectList(criteria);
    }

    public PageInfo<DepartmentMemberPO> findDepartmentMembersPage(DepartmentMemberCriteria criteria) {
        return departmentMemberDAO.selectPage(criteria);
    }

    public List<DepartmentMemberPO> findDepartmentMembersByDepartmentId(String departmentId) {
        return departmentMemberDAO.selectListByDepartmentId(departmentId);
    }

    public List<DepartmentMemberPO> findDepartmentMembersByUserId(String userId) {
        return departmentMemberDAO.selectListByUserId(userId);
    }

    //endregion
}

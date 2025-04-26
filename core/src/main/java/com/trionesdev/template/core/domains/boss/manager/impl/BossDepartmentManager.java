package com.trionesdev.template.core.domains.boss.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.commons.core.constant.IdentityConstants;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.template.core.domains.boss.dao.criteria.BossDepartmentCriteria;
import com.trionesdev.template.core.domains.boss.dao.criteria.BossDepartmentMemberCriteria;
import com.trionesdev.template.core.domains.boss.dao.impl.BossDepartmentDAO;
import com.trionesdev.template.core.domains.boss.dao.impl.BossDepartmentMemberDAO;
import com.trionesdev.template.core.domains.boss.dao.po.BossDepartmentMemberPO;
import com.trionesdev.template.core.domains.boss.dao.po.BossDepartmentPO;
import com.trionesdev.template.core.domains.boss.repository.aggregate.entity.BossUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BossDepartmentManager {
    private final BossDepartmentDAO departmentDAO;
    private final BossDepartmentMemberDAO departmentMemberDAO;

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

    public void createDepartment(BossDepartmentPO department) {
        department.setPrevIds(findPrevIds(department.getParentId()));
        departmentDAO.save(department);
    }

    public void deleteDepartmentById(String id) {
        departmentDAO.removeById(id);
    }

    public void updateDepartmentById(BossDepartmentPO department) {
        if (Objects.equals(department.getId(), department.getParentId())) {
            throw new BusinessException("DEPARTMENT_SELF_PARENT");
        }
        department.setPrevIds(findPrevIds(department.getParentId()));
        departmentDAO.updateById(department);
    }

    public Optional<BossDepartmentPO> findDepartmentById(String id) {
        return Optional.ofNullable(departmentDAO.getById(id));
    }

    public List<BossDepartmentPO> findDepartments() {
        return departmentDAO.list();
    }

    public List<BossDepartmentPO> findDepartments(BossDepartmentCriteria criteria) {
        return departmentDAO.selectList(criteria);
    }

    public List<BossDepartmentPO> findDepartmentsByParentId(String parentId) {
        return departmentDAO.selectListByParentId(parentId);
    }

    public List<BossDepartmentPO> findDepartmentsByIds(Collection<String> ids) {
        if (CollectionUtil.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return departmentDAO.listByIds(ids);
    }

    //endregion

    //region department member
    public void createDepartmentMember(BossDepartmentMemberPO departmentMember) {
        departmentMemberDAO.save(departmentMember);
    }

    public void deleteDepartmentMemberById(String id) {
        departmentMemberDAO.removeById(id);
    }

    public void updateDepartmentMemberById(BossDepartmentMemberPO departmentMember) {
        departmentMemberDAO.updateById(departmentMember);
    }

    public void setMemberDepartments(BossUser user, List<String> departmentIds) {
        Objects.requireNonNull(user.getId());
        departmentMemberDAO.deleteByUserId(user.getId());
        if (CollectionUtil.isEmpty(departmentIds)) {
            return;
        }
        List<BossDepartmentMemberPO> departmentMembers = departmentIds.stream().map(departmentId -> {
            return BossDepartmentMemberPO.builder().userId(user.getId()).departmentId(departmentId).build();
        }).collect(Collectors.toList());
        departmentMemberDAO.saveBatch(departmentMembers);
    }

    public Optional<BossDepartmentMemberPO> findDepartmentMemberById(String id) {
        return Optional.ofNullable(departmentMemberDAO.getById(id));
    }

    public List<BossDepartmentMemberPO> findDepartmentMembers(BossDepartmentMemberCriteria criteria) {
        return departmentMemberDAO.selectList(criteria);
    }

    public PageInfo<BossDepartmentMemberPO> findDepartmentMembersPage(BossDepartmentMemberCriteria criteria) {
        return departmentMemberDAO.selectPage(criteria);
    }

    public List<BossDepartmentMemberPO> findDepartmentMembersByDepartmentId(String departmentId) {
        return departmentMemberDAO.selectListByDepartmentId(departmentId);
    }

    public List<BossDepartmentMemberPO> findDepartmentMembersByUserId(String userId) {
        return departmentMemberDAO.selectListByUserId(userId);
    }

    //endregion
}

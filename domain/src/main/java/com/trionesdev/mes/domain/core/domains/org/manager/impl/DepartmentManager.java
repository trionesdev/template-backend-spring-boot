package com.trionesdev.mes.domain.core.domains.org.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.org.repository.criteria.DepartmentMemberCriteria;
import com.trionesdev.mes.domain.core.domains.org.repository.impl.DepartmentMemberRepository;
import com.trionesdev.mes.domain.core.domains.org.repository.impl.DepartmentRepository;
import com.trionesdev.mes.domain.core.domains.org.repository.po.DepartmentMemberPO;
import com.trionesdev.mes.domain.core.domains.org.repository.po.DepartmentPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DepartmentManager {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMemberRepository departmentMemberRepository;

    //region department
    public void createDepartment(DepartmentPO department) {
        departmentRepository.save(department);
    }

    public void deleteDepartmentById(String id) {
        departmentRepository.removeById(id);
    }

    public void updateDepartmentById(DepartmentPO department) {
        departmentRepository.updateById(department);
    }

    public Optional<DepartmentPO> findDepartmentById(String id) {
        return Optional.ofNullable(departmentRepository.getById(id));
    }

    public List<DepartmentPO> findDepartments() {
        return departmentRepository.list();
    }
    //endregion

    //region department member
    public void createDepartmentMember(DepartmentMemberPO departmentMember) {
        departmentMemberRepository.save(departmentMember);
    }

    public void deleteDepartmentMemberById(String id) {
        departmentMemberRepository.removeById(id);
    }

    public void updateDepartmentMemberById(DepartmentMemberPO departmentMember) {
        departmentMemberRepository.updateById(departmentMember);
    }

    public Optional<DepartmentMemberPO> findDepartmentMemberById(String id) {
        return Optional.ofNullable(departmentMemberRepository.getById(id));
    }

    public List<DepartmentMemberPO> findDepartmentMembers(DepartmentMemberCriteria criteria) {
        return departmentMemberRepository.selectList(criteria);
    }

    public PageInfo<DepartmentMemberPO> findDepartmentMembersPage(DepartmentMemberCriteria criteria) {
        return departmentMemberRepository.selectPage(criteria);
    }

    //endregion
}

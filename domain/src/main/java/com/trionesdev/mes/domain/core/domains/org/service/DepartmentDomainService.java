package com.trionesdev.mes.domain.core.domains.org.service;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.org.repository.criteria.DepartmentMemberCriteria;
import com.trionesdev.mes.domain.core.domains.org.repository.po.DepartmentPO;
import com.trionesdev.mes.domain.core.domains.org.service.bo.DepartmentTreeArg;
import com.trionesdev.mes.domain.core.dto.org.DepartmentDTO;
import com.trionesdev.mes.domain.core.dto.org.DepartmentMemberDTO;
import com.trionesdev.mes.domain.core.dto.org.SetMemberDepartmentsArg;

import java.util.List;
import java.util.Optional;

public interface DepartmentDomainService {


    void createDepartment(DepartmentPO department);

    void deleteDepartmentById(String id);

    void updateDepartmentById(DepartmentPO department);

    Optional<DepartmentDTO> findDepartmentById(String id);

    List<DepartmentPO> findDepartments();

    List<Tree<String>> departmentTree(DepartmentTreeArg arg);

    void setMemberDepartments(SetMemberDepartmentsArg arg);

    List<DepartmentMemberDTO> findDepartmentMembersByMemberId(String memberId);

    List<DepartmentMemberDTO> findDepartmentMembers(DepartmentMemberCriteria criteria);

    PageInfo<DepartmentMemberDTO> findDepartmentMembersPage(DepartmentMemberCriteria criteria);

    List<Tree<String>> orgTree();


}

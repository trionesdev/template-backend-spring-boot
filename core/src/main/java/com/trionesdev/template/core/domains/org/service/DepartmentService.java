package com.trionesdev.template.core.domains.org.service;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.template.core.domains.org.dao.po.DepartmentPO;
import com.trionesdev.template.core.domains.org.dto.*;
import com.trionesdev.template.core.domains.org.dao.criteria.DepartmentMemberCriteria;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {


    void createDepartment(DepartmentPO department);

    void deleteDepartmentById(String id);

    void updateDepartmentById(DepartmentPO department);

    Optional<DepartmentDTO> findDepartmentById(String id);

    List<DepartmentPO> findDepartments();

    List<DepartmentDTO> findDepartmentPaths(String id);

    List<Tree<String>> departmentTree(DepartmentTreeQuery arg);

    void setMemberDepartments(SetMemberDepartmentsCmd arg);

    void deleteDepartmentMemberById(String id);

    List<DepartmentMemberDTO> findDepartmentMembersByUserId(String memberId);

    List<DepartmentMemberDTO> findDepartmentMembers(DepartmentMemberCriteria criteria);

    PageInfo<DepartmentMemberDTO> findDepartmentMembersPage(DepartmentMemberCriteria criteria);

    List<Tree<String>> orgTree();

    List<OrgNodeDTO> orgListByDepartmentId(String departmentId);
}

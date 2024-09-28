package com.trionesdev.wms.core.domains.org.service;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.org.dao.po.DepartmentPO;
import com.trionesdev.wms.core.domains.org.dto.OrgNodeDTO;
import com.trionesdev.wms.core.domains.org.dto.SetMemberDepartmentsCmd;
import com.trionesdev.wms.core.domains.org.service.bo.DepartmentTreeArg;
import com.trionesdev.wms.core.domains.org.dao.criteria.DepartmentMemberCriteria;
import com.trionesdev.wms.core.domains.org.dto.DepartmentDTO;
import com.trionesdev.wms.core.domains.org.dto.DepartmentMemberDTO;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {


    void createDepartment(DepartmentPO department);

    void deleteDepartmentById(String id);

    void updateDepartmentById(DepartmentPO department);

    Optional<DepartmentDTO> findDepartmentById(String id);

    List<DepartmentPO> findDepartments();

    List<DepartmentDTO> findDepartmentPaths(String id);

    List<Tree<String>> departmentTree(DepartmentTreeArg arg);

    void setMemberDepartments(SetMemberDepartmentsCmd arg);

    void deleteDepartmentMemberById(String id);

    List<DepartmentMemberDTO> findDepartmentMembersByMemberId(String memberId);

    List<DepartmentMemberDTO> findDepartmentMembers(DepartmentMemberCriteria criteria);

    PageInfo<DepartmentMemberDTO> findDepartmentMembersPage(DepartmentMemberCriteria criteria);

    List<Tree<String>> orgTree();

    List<OrgNodeDTO> orgList(String departmentId);
}

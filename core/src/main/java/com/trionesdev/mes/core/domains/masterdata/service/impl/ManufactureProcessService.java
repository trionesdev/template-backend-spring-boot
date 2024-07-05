package com.trionesdev.mes.core.domains.masterdata.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.core.domains.custom.provider.impl.CustomProvider;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.ManufactureProcessCriteria;
import com.trionesdev.mes.core.domains.masterdata.dao.po.DefectivePO;
import com.trionesdev.mes.core.domains.masterdata.dao.po.ManufactureProcessPO;
import com.trionesdev.mes.core.domains.masterdata.dao.po.ProcessFlowItemPO;
import com.trionesdev.mes.core.domains.masterdata.dto.DefectiveDTO;
import com.trionesdev.mes.core.domains.masterdata.dto.ManufactureProcessDTO;
import com.trionesdev.mes.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.core.domains.masterdata.internal.enums.ProcessGrantObjType;
import com.trionesdev.mes.core.domains.masterdata.manager.impl.DefectiveManager;
import com.trionesdev.mes.core.domains.masterdata.manager.impl.ManufactureProcessManager;
import com.trionesdev.mes.core.domains.masterdata.manager.impl.ProcessFlowManager;
import com.trionesdev.mes.core.domains.org.dto.DepartmentDTO;
import com.trionesdev.mes.core.domains.org.dto.TenantMemberDetailDTO;
import com.trionesdev.mes.core.domains.org.provider.OrgProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ManufactureProcessService {
    private final MasterDataBeanConvert convert;
    private final ManufactureProcessManager manufactureProcessManager;
    private final ProcessFlowManager processFlowManager;
    private final DefectiveManager defectiveManager;
    private final CustomProvider customProvider;
    private final OrgProvider orgProvider;

    public void create(ManufactureProcessDTO manufactureProcess) {
        var process = convert.processDtoToPo(manufactureProcess);
        if (StrUtil.isBlank(manufactureProcess.getCode())) {
            process.setCode(customProvider.generateCode("MANUFACTURE_PROCESS"));
        }
        manufactureProcessManager.create(process);
    }

    public void deleteById(String id) {
        manufactureProcessManager.deleteById(id);
    }

    public void updateById(ManufactureProcessDTO manufactureProcess) {
        var process = convert.processDtoToPo(manufactureProcess);
        manufactureProcessManager.updateById(process);
    }

    public Optional<ManufactureProcessDTO> findByCode(String code) {
        return manufactureProcessManager.findByCode(code).map(this::assembleProcess);
    }

    public Optional<ManufactureProcessDTO> findById(String id) {
        return manufactureProcessManager.findById(id).map(this::assembleProcess);
    }

    public List<ManufactureProcessDTO> findList(ManufactureProcessCriteria criteria) {
        List<ManufactureProcessPO> list = manufactureProcessManager.findList(criteria);
        if (CollectionUtil.isEmpty(list)) {
            return Collections.emptyList();
        }
        return assembleBatch(list);
    }

    public PageInfo<ManufactureProcessDTO> findPage(ManufactureProcessCriteria criteria) {
        PageInfo<ManufactureProcessPO> pageInfo = manufactureProcessManager.findPage(criteria);
        return PageUtils.of(pageInfo, assembleBatch(pageInfo.getRows()));
    }

    public List<ManufactureProcessDTO> findProcessByCodes(Collection<String> codes) {
        if (CollectionUtil.isEmpty(codes)) {
            return Collections.emptyList();
        }
        List<ManufactureProcessPO> processes = manufactureProcessManager.findListByCodes(codes);
        return assembleBatch(processes);
    }

    private ManufactureProcessDTO assembleProcess(ManufactureProcessPO record) {
        var dto = convert.poToDto(record);
        if (CollectionUtil.isNotEmpty(record.getDefectiveCodes())) {
            dto.setDefectives(defectiveManager.findListByCodes(dto.getDefectiveCodes()).stream().map(convert::poToDto).collect(Collectors.toList()));
        }
        if (CollectionUtil.isNotEmpty(Optional.ofNullable(dto.getPermissionGrant()).map(ManufactureProcessDTO.PermissionGrant::getAssignees).orElse(null))) {
            Set<String> departmentIds = dto.getPermissionGrant().getAssignees().stream().filter(obj -> obj.getObjType() == ProcessGrantObjType.DEPARTMENT).map(ManufactureProcessDTO.GrantObj::getObjId).collect(Collectors.toSet());
            Map<String, DepartmentDTO> departmentsMap = Collections.emptyMap();
            if (CollectionUtil.isNotEmpty(departmentIds)) {
                departmentsMap = orgProvider.getDepartmentsByIds(departmentIds).stream().collect(Collectors.toMap(DepartmentDTO::getId, v -> v, (v1, v2) -> v1));
            }
            Set<String> memberIds = dto.getPermissionGrant().getAssignees().stream().filter(obj -> obj.getObjType() == ProcessGrantObjType.MEMBER).map(ManufactureProcessDTO.GrantObj::getObjId).collect(Collectors.toSet());
            Map<String, TenantMemberDetailDTO> membersMap = Collections.emptyMap();
            if (CollectionUtil.isNotEmpty(memberIds)) {
                membersMap = orgProvider.getMembersByMemberIds(memberIds).stream().collect(Collectors.toMap(TenantMemberDetailDTO::getId, v -> v, (v1, v2) -> v1));
            }

            for (var obj : dto.getPermissionGrant().getAssignees()) {
                if (obj.getObjType() == ProcessGrantObjType.DEPARTMENT) {
                    obj.setName(departmentsMap.get(obj.getObjId()).getName());
                } else if (obj.getObjType() == ProcessGrantObjType.MEMBER) {
                    obj.setName(Optional.ofNullable(membersMap.get(obj.getObjId())).map(TenantMemberDetailDTO::getNickname).orElse(null));
                    obj.setAvatar(Optional.ofNullable(membersMap.get(obj.getObjId())).map(TenantMemberDetailDTO::getAvatar).orElse(null));
                }
            }
        }
        return dto;
    }

    private List<ManufactureProcessDTO> assembleBatch(List<ManufactureProcessPO> records) {
        if (CollectionUtil.isEmpty(records)) {
            return Collections.emptyList();
        }
        Set<String> defectiveCodes = records.stream().map(ManufactureProcessPO::getDefectiveCodes)
                .flatMap(codes -> {
                    if (CollectionUtil.isEmpty(codes)) {
                        return new ArrayList<String>().stream();
                    }
                    return codes.stream();
                }).collect(Collectors.toSet());
        var defectivesMap = defectiveManager.findListByCodes(defectiveCodes).stream().collect(Collectors.toMap(DefectivePO::getCode, v -> v, (v1, v2) -> v1));
        var departmentIds = records.stream().map(ManufactureProcessPO::getPermissionGrant).filter(Objects::nonNull).map(ManufactureProcessPO.PermissionGrant::getAssignees)
                .flatMap(assignees -> {
                    return assignees.stream().filter(obj -> obj.getObjType() == ProcessGrantObjType.DEPARTMENT).map(ManufactureProcessPO.GrantObj::getObjId);
                }).collect(Collectors.toSet());
        Map<String, DepartmentDTO> departmentsMap = Collections.emptyMap();
        if (CollectionUtil.isNotEmpty(departmentIds)) {
            departmentsMap = orgProvider.getDepartmentsByIds(departmentIds).stream().collect(Collectors.toMap(DepartmentDTO::getId, v -> v, (v1, v2) -> v1));
        }
        var memberIds = records.stream().map(ManufactureProcessPO::getPermissionGrant).filter(Objects::nonNull).map(ManufactureProcessPO.PermissionGrant::getAssignees)
                .flatMap(assignees -> {
                    return assignees.stream().filter(obj -> obj.getObjType() == ProcessGrantObjType.MEMBER).map(ManufactureProcessPO.GrantObj::getObjId);
                }).collect(Collectors.toSet());
        Map<String, TenantMemberDetailDTO> membersMap = Collections.emptyMap();
        if (CollectionUtil.isNotEmpty(memberIds)) {
            membersMap = orgProvider.getMembersByMemberIds(memberIds).stream().collect(Collectors.toMap(TenantMemberDetailDTO::getId, v -> v, (v1, v2) -> v1));
        }
        var departmentsMapFinal = departmentsMap;
        var membersMapFinal = membersMap;
        return records.stream().map(po -> {
            var dto = convert.poToDto(po);
            if (CollectionUtil.isNotEmpty(dto.getDefectiveCodes())) {
                dto.setDefectives(dto.getDefectiveCodes().stream().map(defectivesMap::get).map(convert::poToDto).collect(Collectors.toList()));
            }
            if (CollectionUtil.isNotEmpty(Optional.ofNullable(dto.getPermissionGrant()).map(ManufactureProcessDTO.PermissionGrant::getAssignees).orElse(null))) {
                dto.getPermissionGrant().getAssignees().forEach(obj -> {
                    if (obj.getObjType() == ProcessGrantObjType.DEPARTMENT) {
                        obj.setName(Optional.ofNullable(departmentsMapFinal.get(obj.getObjId())).map(DepartmentDTO::getName).orElse(null));
                    } else if (obj.getObjType() == ProcessGrantObjType.MEMBER) {
                        obj.setName(Optional.ofNullable(membersMapFinal.get(obj.getObjId())).map(TenantMemberDetailDTO::getNickname).orElse(null));
                        obj.setAvatar(Optional.ofNullable(membersMapFinal.get(obj.getObjId())).map(TenantMemberDetailDTO::getAvatar).orElse(null));
                    }
                });
            }
            return dto;
        }).collect(Collectors.toList());
    }


    public List<ManufactureProcessDTO> findFlowsProcesses(List<String> flowIds) {
        List<ProcessFlowItemPO> flowItems = processFlowManager.findFlowItemsByFlowIds(flowIds);
        if (CollectionUtil.isEmpty(flowItems)) {
            return Collections.emptyList();
        }
        Set<String> processCodes = flowItems.stream().map(ProcessFlowItemPO::getCode).collect(Collectors.toSet());
        var processes = assembleBatch(manufactureProcessManager.findListByCodes(processCodes));
        var processesMap = processes.stream().collect(Collectors.toMap(ManufactureProcessDTO::getCode, v -> v, (v1, v2) -> v1));
        return flowItems.stream().map(item -> processesMap.get(item.getCode())).collect(Collectors.toList());
    }

}

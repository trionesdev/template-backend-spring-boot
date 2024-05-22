package com.trionesdev.mes.rest.backend.domains.masterdata.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ProcessFlowCriteria;
import com.trionesdev.mes.domain.core.dto.masterdata.ProcessFlowDTO;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.query.ProcessFlowQuery;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro.ProcessFlowCreateRO;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro.ProcessFlowUpdateRO;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataBeRestBeanConvert;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataRestConstants;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ProcessFlow;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.ProcessFlowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "工艺")
@RequiredArgsConstructor
@RestController
@RequestMapping(MasterDataRestConstants.MASTER_DATA_PATH)
public class ProcessFlowController {
    private final MasterDataBeRestBeanConvert masterDataBeRestBeanConvert;
    private final ProcessFlowService processFlowService;

    @Operation(summary = "创建工艺")
    @PostMapping("process-flows")
    public void createProcessFlow(@Validated @RequestBody ProcessFlowCreateRO args) {
        ProcessFlow processFlow = masterDataBeRestBeanConvert.from(args);
        processFlowService.createProcessFlow(processFlow);
    }

    @Operation(summary = "根据ID删除工艺")
    @DeleteMapping("process-flows/{id}")
    public void deleteProcessFlowById(@PathVariable String id) {
        processFlowService.deleteProcessFlowById(id);
    }

    @Operation(summary = "根据ID更新工艺")
    @PutMapping("process-flows/{id}")
    public void updateProcessFlowById(@PathVariable String id, @Validated @RequestBody ProcessFlowUpdateRO args) {
        ProcessFlow processFlow = masterDataBeRestBeanConvert.from(args);
        processFlow.setId(id);
        processFlowService.updateProcessFlowById(processFlow);
    }

    @Operation(summary = "根据ID查询工艺")
    @GetMapping("process-flows/{id}")
    public ProcessFlowDTO findProcessFlowById(@PathVariable String id) {
        return processFlowService.findProcessFlowById(id).orElse(null);
    }

    @Operation(summary = "查询工艺列表")
    @GetMapping("process-flows/list")
    public List<ProcessFlowDTO> findProcessFlowList(ProcessFlowQuery query) {
        ProcessFlowCriteria criteria = masterDataBeRestBeanConvert.from(query);
        return processFlowService.findProcessFlowList(criteria);
    }

    @Operation(summary = "分页查询工艺")
    @GetMapping("process-flows/page")
    public PageInfo<ProcessFlowDTO> findProcessFlowPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            ProcessFlowQuery query
    ) {
        ProcessFlowCriteria criteria = masterDataBeRestBeanConvert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return processFlowService.findProcessFlowPage(criteria);
    }

}

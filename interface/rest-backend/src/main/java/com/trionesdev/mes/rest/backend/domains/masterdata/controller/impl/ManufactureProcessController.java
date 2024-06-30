package com.trionesdev.mes.rest.backend.domains.masterdata.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.ManufactureProcessCriteria;
import com.trionesdev.mes.core.domains.masterdata.dto.ManufactureProcessDTO;
import com.trionesdev.mes.core.domains.masterdata.service.impl.ManufactureProcessService;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.query.ManufactureProcessQuery;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.query.ProcessesByFlowIdsQuery;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro.ManufactureProcessRO;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataBeRestBeanConvert;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "生产工艺")
@RequiredArgsConstructor
@RestController
@RequestMapping(MasterDataRestConstants.MASTER_DATA_PATH)
public class ManufactureProcessController {
    private final MasterDataBeRestBeanConvert convert;
    private final ManufactureProcessService manufactureProcessService;

    @Operation(summary = "创建生产工序")
    @PostMapping("manufacture-processes")
    public void create(@Validated @RequestBody ManufactureProcessRO.Create args) {
        var manufactureProcess = convert.from(args);
        manufactureProcessService.create(manufactureProcess);
    }

    @Operation(summary = "根据ID删除生产工序")
    @DeleteMapping("manufacture-processes/{id}")
    public void deleteById(@PathVariable("id") String id) {
        manufactureProcessService.deleteById(id);
    }

    @Operation(summary = "根据ID更新生产工序")
    @PutMapping("manufacture-processes/{id}")
    public void updateById(@PathVariable("id") String id, @Validated @RequestBody ManufactureProcessRO.Update args) {
        var manufactureProcess = convert.from(args);
        manufactureProcess.setId(id);
        manufactureProcessService.updateById(manufactureProcess);
    }

    @Operation(summary = "根据ID查询生产工序")
    @GetMapping("manufacture-processes/{id}")
    public ManufactureProcessDTO findById(@PathVariable("id") String id) {
        return manufactureProcessService.findById(id).orElse(null);
    }

    @Operation(summary = "根据Code查询生产工序")
    @GetMapping("manufacture-processes/code/{code}")
    public ManufactureProcessDTO findByCode(@PathVariable("code") String code) {
        return manufactureProcessService.findByCode(code).orElse(null);
    }

    @Operation(summary = "查询生产工序列表")
    @GetMapping("manufacture-processes/list")
    public List<ManufactureProcessDTO> findList(ManufactureProcessQuery query) {
        ManufactureProcessCriteria manufactureProcessCriteria = convert.from(query);
        return manufactureProcessService.findList(manufactureProcessCriteria);
    }

    @Operation(summary = "分页查询生产工序")
    @GetMapping("manufacture-processes/page")
    public PageInfo<ManufactureProcessDTO> findPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            ManufactureProcessQuery query
    ) {
        ManufactureProcessCriteria manufactureProcessCriteria = convert.from(query);
        manufactureProcessCriteria.setPageNum(pageNum);
        manufactureProcessCriteria.setPageSize(pageSize);
        return manufactureProcessService.findPage(manufactureProcessCriteria);
    }

    @Operation(summary = "根据工艺IDS查询工序列表")
    @PostMapping("manufacture-processes/by-flow-ids")
    public List<ManufactureProcessDTO> findProcessesByFlowIds(@RequestBody ProcessesByFlowIdsQuery query) {
        return manufactureProcessService.findFlowsProcesses(query.getFlowIds());
    }

}

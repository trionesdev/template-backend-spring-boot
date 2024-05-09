package com.trionesdev.mes.rest.backend.domains.masterdata.controller.impl;

import com.trionesdev.mes.rest.backend.domains.masterdata.controller.query.ManufactureProcessQuery;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro.ManufactureProcessCreateRO;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro.ManufactureProcessUpdateRO;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataBeRestBeanConvert;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataRestConstants;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ManufactureProcessCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.po.ManufactureProcessPO;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.ManufactureProcessService;
import com.trionesdev.mes.domain.core.dto.masterdata.ManufactureProcessDTO;
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

@Tag(name = "生产工艺")
@RequiredArgsConstructor
@RestController
@RequestMapping(MasterDataRestConstants.MASTER_DATA_PATH)
public class ManufactureProcessController {
    private final MasterDataBeRestBeanConvert masterDataBeRestBeanConvert;
    private final ManufactureProcessService manufactureProcessService;

    @Operation(summary = "创建生产工序")
    @PostMapping("manufacture-processes")
    public void create(@Validated @RequestBody ManufactureProcessCreateRO args) {
        ManufactureProcessPO manufactureProcess = masterDataBeRestBeanConvert.from(args);
        manufactureProcessService.create(manufactureProcess);
    }

    @Operation(summary = "根据ID删除生产工序")
    @DeleteMapping("manufacture-processes/{id}")
    public void deleteById(@PathVariable("id") String id) {
        manufactureProcessService.deleteById(id);
    }

    @Operation(summary = "根据ID更新生产工序")
    @PutMapping("manufacture-processes/{id}")
    public void updateById(@PathVariable("id") String id, @Validated @RequestBody ManufactureProcessUpdateRO args) {
        ManufactureProcessPO manufactureProcess = masterDataBeRestBeanConvert.from(args);
        manufactureProcess.setId(id);
        manufactureProcessService.updateById(manufactureProcess);
    }

    @Operation(summary = "根据ID查询生产工序")
    @GetMapping("manufacture-processes/{id}")
    public ManufactureProcessDTO findById(@PathVariable("id") String id) {
        return manufactureProcessService.findById(id).orElse(null);
    }

    @Operation(summary = "分页查询生产工序")
    @GetMapping("manufacture-processes/page")
    public PageInfo<ManufactureProcessDTO> findPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            ManufactureProcessQuery query
    ) {
        ManufactureProcessCriteria manufactureProcessCriteria = masterDataBeRestBeanConvert.from(query);
        manufactureProcessCriteria.setPageNum(pageNum);
        manufactureProcessCriteria.setPageSize(pageSize);
        return manufactureProcessService.findPage(manufactureProcessCriteria);
    }

}

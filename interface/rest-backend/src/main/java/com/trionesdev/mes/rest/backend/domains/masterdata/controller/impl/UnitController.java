package com.trionesdev.mes.rest.backend.domains.masterdata.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.UnitCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.UnitPO;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro.UnitCreateRO;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro.UnitUpdateRO;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataBeRestBeanConvert;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataRestConstants;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.UnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "单位")
@RequiredArgsConstructor
@RestController
@RequestMapping(MasterDataRestConstants.MASTER_DATA_PATH)
public class UnitController {
    private final MasterDataBeRestBeanConvert masterDataBeRestBeanConvert;
    private final UnitService unitService;

    @Operation(summary = "创建单位")
    @PostMapping("units")
    public void create(@Validated @RequestBody UnitCreateRO args) {
        UnitPO unit = masterDataBeRestBeanConvert.from(args);
        unitService.create(unit);
    }

    @Operation(summary = "根据ID删除单位")
    @DeleteMapping("units/{id}")
    public void deleteById(@PathVariable("id") String id) {
        unitService.deleteById(id);
    }

    @Operation(summary = "根据ID更新单位")
    @PutMapping("units/{id}")
    public void updateById(@PathVariable("id") String id, @Validated @RequestBody UnitUpdateRO args) {
        UnitPO unit = masterDataBeRestBeanConvert.from(args);
        unit.setId(id);
        unitService.updateById(unit);
    }

    @Operation(summary = "查询单位列表(分页)")
    @GetMapping("units/page")
    public PageInfo<UnitPO> findPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize
    ) {
        UnitCriteria criteria = new UnitCriteria();
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return unitService.findPage(criteria);
    }

}

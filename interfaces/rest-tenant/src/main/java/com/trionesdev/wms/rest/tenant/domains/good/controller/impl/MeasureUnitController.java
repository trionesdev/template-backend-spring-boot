package com.trionesdev.wms.rest.tenant.domains.good.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.good.dao.po.MeasureUnitPO;
import com.trionesdev.wms.core.domains.good.service.impl.MeasureUnitService;
import com.trionesdev.wms.rest.tenant.domains.good.controller.ro.MeasureUnitQueryRO;
import com.trionesdev.wms.rest.tenant.domains.good.controller.ro.MeasureUnitRO;
import com.trionesdev.wms.rest.tenant.domains.good.internal.GoodBeRestConvert;
import com.trionesdev.wms.rest.tenant.domains.good.internal.GoodRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@Tag(name = "单位管理")
@RequiredArgsConstructor
@RestController
@RequestMapping(GoodRestConstants.GOOD_PATH)
public class MeasureUnitController {
    private final GoodBeRestConvert convert;
    private final MeasureUnitService measureUnitService;

    @Operation(summary = "创建单位")
    @PostMapping("measure-units")
    public void create(@Validated @RequestBody MeasureUnitRO args) {
        var po = convert.from(args);
        measureUnitService.create(po);
    }

    @Operation(summary = "根据ID更新单位")
    @PutMapping("measure-units/{id}")
    public void updateById(
            @PathVariable(value = "id") String id,
            @Validated @RequestBody MeasureUnitRO args
    ) {
        var po = convert.from(args);
        po.setId(id);
        measureUnitService.updateById(po);
    }

    @Operation(summary = "根据ID查询单位")
    @GetMapping(value = "measure-units/{id}")
    public MeasureUnitPO queryById(@PathVariable String id) {
        return measureUnitService.findById(id).orElse(null);
    }

    @Operation(summary = "查询单位列表分页")
    @GetMapping("measure-units/page")
    public PageInfo<MeasureUnitPO> queryPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            MeasureUnitQueryRO query) {
        var criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return measureUnitService.findPage(criteria);
    }

    @Operation(summary = "查询单位列表")
    @GetMapping("measure-units/list")
    public List<MeasureUnitPO> queryList(MeasureUnitQueryRO query) {
        var criteria = convert.from(query);
        return measureUnitService.findList(criteria);
    }

    @Operation(summary = "批量删除单位")
    @Parameter(name = "ids", description = "以,分割的id字符串，形如：1,2,3")
    @DeleteMapping(value = "measure-units/{ids}")
    public void deleteByIds(@PathVariable(value = "ids") List<String> ids) {
        measureUnitService.deleteByIds(ids);
    }
}

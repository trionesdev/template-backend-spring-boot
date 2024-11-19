package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseAreaDTO;
import com.trionesdev.wms.core.domains.warehouse.service.impl.WarehouseAreaService;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseAreaQueryRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseAreaRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.internal.WarehouseBeRestConvert;
import com.trionesdev.wms.rest.tenant.domains.warehouse.internal.WarehouseRestConstants;
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

@Tag(name = "库区管理")
@RequiredArgsConstructor
@RestController
@RequestMapping(WarehouseRestConstants.WAREHOUSE_PATH)
public class WarehouseAreaController {
    private final WarehouseBeRestConvert convert;
    private final WarehouseAreaService warehouseAreaService;

    @Operation(summary = "创建库区")
    @PostMapping("warehouse-areas")
    public void create(@Validated @RequestBody WarehouseAreaRO args) {
        var po = convert.from(args);
        warehouseAreaService.create(po);
    }

    @Operation(summary = "根据ID更新库区")
    @PutMapping("warehouse-areas/{id}")
    public void updateById(
            @PathVariable(value = "id") String id,
            @Validated @RequestBody WarehouseAreaRO args
    ) {
        var po = convert.from(args);
        po.setId(id);
        warehouseAreaService.updateById(po);
    }

    @Operation(summary = "根据ID查询库区")
    @GetMapping(value = "warehouse-areas/{id}")
    public WarehouseAreaDTO queryById(@PathVariable String id) {
        return warehouseAreaService.findById(id).orElse(null);
    }

    @Operation(summary = "查询库区列表分页")
    @GetMapping("warehouse-areas/page")
    public PageInfo<WarehouseAreaDTO> queryPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            WarehouseAreaQueryRO query) {
        var criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return warehouseAreaService.findPage(criteria);
    }

    @Operation(summary = "查询库区列表")
    @GetMapping("warehouse-areas/list")
    public List<WarehouseAreaDTO> queryList(WarehouseAreaQueryRO query) {
        var criteria = convert.from(query);
        return warehouseAreaService.findList(criteria);
    }

    @Operation(summary = "批量删除库区")
    @Parameter(name = "ids", description = "以,分割的id字符串，形如：1,2,3")
    @DeleteMapping(value = "warehouse-areas/{ids}")
    public void deleteByIds(@PathVariable(value = "ids") List<String> ids) {
        warehouseAreaService.deleteByIds(ids);
    }
}

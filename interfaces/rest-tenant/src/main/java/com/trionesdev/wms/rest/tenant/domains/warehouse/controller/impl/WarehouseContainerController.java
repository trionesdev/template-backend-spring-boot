package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseContainerDTO;
import com.trionesdev.wms.core.domains.warehouse.service.impl.WarehouseContainerService;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseContainerQueryRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseContainerRO;
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

@Tag(name = "托盘管理")
@RequiredArgsConstructor
@RestController
@RequestMapping(WarehouseRestConstants.WAREHOUSE_PATH)
public class WarehouseContainerController {
    private final WarehouseBeRestConvert convert;
    private final WarehouseContainerService warehouseContainerService;

    @Operation(summary = "创建托盘")
    @PostMapping("warehouse-containers")
    public void create(@Validated @RequestBody WarehouseContainerRO args) {
        var warehouse = convert.from(args);
        warehouseContainerService.create(warehouse);
    }

    @Operation(summary = "根据ID更新托盘")
    @PutMapping("warehouse-containers/{id}")
    public void updateById(
            @PathVariable(value = "id") String id,
            @Validated @RequestBody WarehouseContainerRO args
    ) {
        var warehouse = convert.from(args);
        warehouse.setId(id);
        warehouseContainerService.updateById(warehouse);
    }

    @Operation(summary = "根据ID查询托盘")
    @GetMapping(value = "warehouse-containers/{id}")
    public WarehouseContainerDTO queryById(@PathVariable String id) {
        return warehouseContainerService.findById(id).orElse(null);
    }

    @Operation(summary = "查询托盘列表分页")
    @GetMapping("warehouse-containers/page")
    public PageInfo<WarehouseContainerDTO> queryPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            WarehouseContainerQueryRO query) {
        var criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return warehouseContainerService.findPage(criteria);
    }

    @Operation(summary = "查询托盘列表")
    @GetMapping("warehouse-containers/list")
    public List<WarehouseContainerDTO> queryList(WarehouseContainerQueryRO query) {
        var criteria = convert.from(query);
        return warehouseContainerService.findList(criteria);
    }

    @Operation(summary = "批量删除托盘")
    @Parameter(name = "ids", description = "以,分割的id字符串，形如：1,2,3")
    @DeleteMapping(value = "warehouse-containers/{ids}")
    public void deleteByIds(@PathVariable(value = "ids") List<String> ids) {
        warehouseContainerService.deleteByIds(ids);
    }
}

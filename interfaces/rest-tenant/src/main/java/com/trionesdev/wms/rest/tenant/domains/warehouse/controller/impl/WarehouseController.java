package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseDTO;
import com.trionesdev.wms.core.domains.warehouse.service.impl.WarehouseService;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseQueryRO;
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

@Tag(name = "仓库管理")
@RequiredArgsConstructor
@RestController
@RequestMapping(WarehouseRestConstants.WAREHOUSE_PATH)
public class WarehouseController {

    private final WarehouseBeRestConvert convert;
    private final WarehouseService warehouseService;

    @Operation(summary = "创建仓库")
    @PostMapping("warehouses")
    public void create(@Validated @RequestBody WarehouseRO args) {
        var po = convert.from(args);
        warehouseService.createWarehouse(po);
    }

    @Operation(summary = "根据ID更新仓库")
    @PutMapping("warehouses/{id}")
    public void updateById(
            @PathVariable(value = "id") String id,
            @Validated @RequestBody WarehouseRO args
    ) {
        var po = convert.from(args);
        po.setId(id);
        warehouseService.updateWarehouseById(po);
    }

    @Operation(summary = "根据ID查询仓库")
    @GetMapping(value = "warehouses/{id}")
    public WarehouseDTO queryById(@PathVariable String id) {
        return warehouseService.findById(id).orElse(null);
    }

    @Operation(summary = "查询仓库列表分页")
    @GetMapping("warehouses/page")
    public PageInfo<WarehouseDTO> queryPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            WarehouseQueryRO query) {
        var criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return warehouseService.findPage(criteria);
    }

    @Operation(summary = "查询仓库列表")
    @GetMapping("warehouses/list")
    public List<WarehouseDTO> queryList(WarehouseQueryRO query) {
        var criteria = convert.from(query);
        return warehouseService.findList(criteria);
    }

    @Operation(summary = "批量删除仓库")
    @Parameter(name = "ids", description = "以,分割的id字符串，形如：1,2,3")
    @DeleteMapping(value = "warehouses/{ids}")
    public void deleteByIds(@PathVariable(value = "ids") List<String> ids) {
        warehouseService.deleteByIds(ids);
    }
}

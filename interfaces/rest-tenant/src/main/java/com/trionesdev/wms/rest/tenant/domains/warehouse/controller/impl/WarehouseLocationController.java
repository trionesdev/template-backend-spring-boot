package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseLocationDTO;
import com.trionesdev.wms.core.domains.warehouse.service.impl.WarehouseLocationService;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseLocationQueryRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseLocationRO;
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

@Tag(name = "库位管理")
@RequiredArgsConstructor
@RestController
@RequestMapping(WarehouseRestConstants.WAREHOUSE_PATH)
public class WarehouseLocationController {
    private final WarehouseBeRestConvert convert;
    private final WarehouseLocationService warehouseLocationService;

    @Operation(summary = "创建库位")
    @PostMapping("warehouse-locations")
    public void create(@Validated @RequestBody WarehouseLocationRO args) {
        var po = convert.from(args);
        warehouseLocationService.create(po);
    }

    @Operation(summary = "根据ID更新库位")
    @PutMapping("warehouse-locations/{id}")
    public void updateById(
            @PathVariable(value = "id") String id,
            @Validated @RequestBody WarehouseLocationRO args
    ) {
        var po = convert.from(args);
        po.setId(id);
        warehouseLocationService.updateById(po);
    }

    @Operation(summary = "根据ID查询库位")
    @GetMapping(value = "warehouse-locations/{id}")
    public WarehouseLocationDTO queryById(@PathVariable String id) {
        return warehouseLocationService.findById(id).orElse(null);
    }

    @Operation(summary = "查询库位列表分页")
    @GetMapping("warehouse-locations/page")
    public PageInfo<WarehouseLocationDTO> queryPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            WarehouseLocationQueryRO query) {
        var criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return warehouseLocationService.findPage(criteria);
    }

    @Operation(summary = "查询库位列表")
    @GetMapping("warehouse-locations/list")
    public List<WarehouseLocationDTO> queryList(WarehouseLocationQueryRO query) {
        var criteria = convert.from(query);
        return warehouseLocationService.findList(criteria);
    }

    @Operation(summary = "批量删除库位")
    @Parameter(name = "ids", description = "以,分割的id字符串，形如：1,2,3")
    @DeleteMapping(value = "warehouse-locations/{ids}")
    public void deleteByIds(@PathVariable(value = "ids") List<String> ids) {
        warehouseLocationService.deleteByIds(ids);
    }
}

package com.trionesdev.mes.rest.backend.domains.warehouse.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.warehouse.repository.criteria.WarehouseCriteria;
import com.trionesdev.mes.domain.core.domains.warehouse.repository.po.WarehousePO;
import com.trionesdev.mes.domain.core.domains.warehouse.service.impl.WarehouseService;
import com.trionesdev.mes.domain.core.dto.warehouse.WarehouseDTO;
import com.trionesdev.mes.rest.backend.domains.warehouse.controller.query.WarehouseQuery;
import com.trionesdev.mes.rest.backend.domains.warehouse.controller.ro.WarehouseCreateRO;
import com.trionesdev.mes.rest.backend.domains.warehouse.controller.ro.WarehouseUpdateRO;
import com.trionesdev.mes.rest.backend.domains.warehouse.internal.WarehouseBeRestBeanConvert;
import com.trionesdev.mes.rest.backend.domains.warehouse.internal.WarehouseRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "仓库")
@RequiredArgsConstructor
@RestController
@RequestMapping(WarehouseRestConstants.WAREHOUSE_PATH)
public class WarehouseController {
    private final WarehouseBeRestBeanConvert convert;
    private final WarehouseService warehouseService;

    @Operation(summary = "创建仓库")
    @PostMapping("warehouses")
    public void createWarehouse(@Validated @RequestBody WarehouseCreateRO args) {
        WarehousePO warehouse = convert.from(args);
        warehouseService.createWarehouse(warehouse);
    }

    @Operation(summary = "根据ID更新仓库")
    @DeleteMapping("warehouses/{id}")
    public void deleteWarehouseById(@PathVariable String id) {
        warehouseService.deleteWarehouseById(id);
    }

    @Operation(summary = "根据ID更新仓库")
    @PutMapping("warehouses/{id}")
    public void updateWarehouseById(@PathVariable String id, @Validated @RequestBody WarehouseUpdateRO args) {
        WarehousePO warehouse = convert.from(args);
        warehouse.setId(id);
        warehouseService.updateWarehouseById(warehouse);
    }

    @Operation(summary = "根据ID查询仓库")
    @GetMapping("warehouses/{id}")
    public WarehouseDTO queryWarehouseById(@PathVariable String id) {
        return warehouseService.findById(id).orElse(null);
    }

    @Operation(summary = "查询仓库分页列表")
    @GetMapping("warehouses/page")
    public PageInfo<WarehouseDTO> queryWarehousePage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            WarehouseQuery query
    ) {
        WarehouseCriteria criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return warehouseService.findPage(criteria);
    }

}

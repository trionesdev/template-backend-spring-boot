package com.trionesdev.wms.rest.tenant.domains.warehouse.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.mybatisplus.util.MpPageUtils;
import com.trionesdev.wms.core.domains.warehouse.dao.criteria.WarehouseInboundPlanCriteria;
import com.trionesdev.wms.core.domains.warehouse.dto.WarehouseInboundPlanDTO;
import com.trionesdev.wms.core.domains.warehouse.service.impl.WarehouseInboundPlanService;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseInboundPlanCreateRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseInboundPlanQueryRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.ro.WarehouseInboundPlanUpdateRO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.controller.vo.WarehouseInboundPlanVO;
import com.trionesdev.wms.rest.tenant.domains.warehouse.internal.WarehouseBeRestConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.trionesdev.wms.rest.tenant.domains.warehouse.internal.WarehouseRestConstants.WAREHOUSE_PATH;

@Tag(name = "入库计划")
@Slf4j
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping(value = WAREHOUSE_PATH)
public class WarehouseInboundPlanController {
    private final WarehouseInboundPlanService warehouseInboundPlanService;
    private final WarehouseBeRestConvert convert;

    @Operation(summary = "分页")
    @GetMapping(value = "inbound-plans/page")
    public PageInfo<WarehouseInboundPlanVO> page(@Validated WarehouseInboundPlanQueryRO args) {
        WarehouseInboundPlanCriteria criteria = convert.from(args);

        PageInfo<WarehouseInboundPlanDTO> page = warehouseInboundPlanService.page(criteria);
        List<WarehouseInboundPlanVO> rows = convert.fromInboundPlanDTO(page.getRows());

        return MpPageUtils.of(page, rows);
    }

    @Operation(summary = "新增")
    @PostMapping(value = "inbound-plans")
    public void save(@Validated @RequestBody WarehouseInboundPlanCreateRO args) {
        WarehouseInboundPlanDTO dto = convert.from(args);
        warehouseInboundPlanService.save(dto);
    }

    @Operation(summary = "编辑")
    @PutMapping(value = "inbound-plans")
    public void update(@Validated @RequestBody WarehouseInboundPlanUpdateRO args) {
        WarehouseInboundPlanDTO dto = convert.from(args);
        warehouseInboundPlanService.updateById(dto);
    }

    @Operation(summary = "详情")
    @GetMapping(value = "inbound-plans/{id}")
    public WarehouseInboundPlanVO getById(@PathVariable("id") String id) {
        WarehouseInboundPlanDTO bo = warehouseInboundPlanService.getById(id);
        return convert.from(bo);
    }

    @Operation(summary = "删除")
    @DeleteMapping(value = "inbound-plans/{id}")
    public void deleteById(@PathVariable("id") String id) {
        warehouseInboundPlanService.removeById(id);
    }

    @Operation(summary = "批量删除")
    @DeleteMapping(value = "inbound-plans")
    public void deleteById(@RequestBody List<String> ids) {
        warehouseInboundPlanService.removeById(ids);
    }

    @Operation(summary = "取消")
    @PutMapping(value = "inbound-plans/cancel/{id}")
    public void cancel(@PathVariable("id") String id) {
        warehouseInboundPlanService.cancel(id);
    }
}

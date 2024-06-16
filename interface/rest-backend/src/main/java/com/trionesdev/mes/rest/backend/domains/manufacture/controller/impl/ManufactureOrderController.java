package com.trionesdev.mes.rest.backend.domains.manufacture.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.manufacture.service.impl.ManufactureOrderService;
import com.trionesdev.mes.domain.core.dto.manufacture.ManufactureOrderDTO;
import com.trionesdev.mes.domain.core.dto.manufacture.ManufactureOrderTaskDTO;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.query.ManufactureOrderQuery;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.query.ManufactureOrderTaskQuery;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro.ManufactureOrderRO;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro.ManufactureOrderTaskRO;
import com.trionesdev.mes.rest.backend.domains.manufacture.internal.ManufactureBeBeanConvert;
import com.trionesdev.mes.rest.backend.domains.manufacture.internal.ManufactureConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "生产工单")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = ManufactureConstants.MANUFACTURE_PATH)
public class ManufactureOrderController {
    private final ManufactureBeBeanConvert convert;
    private final ManufactureOrderService manufactureOrderService;

    @Operation(summary = "创建生产工单")
    @PostMapping("orders")
    public void createOrder(@Validated @RequestBody ManufactureOrderRO.Create args) {
        var order = convert.from(args);
        manufactureOrderService.createManufactureOrder(order);
    }

    @Operation(summary = "根据ID更新生产工单")
    @DeleteMapping("orders/{id}")
    public void deleteOrderById(@PathVariable String id) {
        manufactureOrderService.deleteManufactureOrder(id);
    }

    @Operation(summary = "根据ID修改生产工单")
    @PutMapping("orders/{id}")
    public void updateOrderById(@PathVariable String id, @Validated @RequestBody ManufactureOrderRO.Update args) {
        var order = convert.from(args);
        order.setId(id);
        manufactureOrderService.updateManufactureOrderById(order);
    }

    @Operation(summary = "根据ID获取工单详情")
    @GetMapping(value = "orders/{id}")
    public ManufactureOrderDTO findOrderById(@PathVariable String id) {
        return manufactureOrderService.findManufactureOrderById(id).orElse(null);
    }

    @Operation(summary = "查询工单列表")
    @GetMapping(value = "orders/list")
    public List<ManufactureOrderDTO> findOrderList(ManufactureOrderQuery query) {
        var criteria = convert.from(query);
        return manufactureOrderService.findOrderList(criteria);
    }

    @Operation(summary = "查询工单列表分页")
    @GetMapping(value = "orders/page")
    public PageInfo<ManufactureOrderDTO> findOrdersPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            ManufactureOrderQuery query
    ) {
        var manufactureOrderCriteria = convert.from(query);
        manufactureOrderCriteria.setPageNum(pageNum);
        manufactureOrderCriteria.setPageSize(pageSize);
        return manufactureOrderService.findManufactureOrderPage(manufactureOrderCriteria);
    }

    @Operation(summary = "查询工单任务列表分页")
    @GetMapping(value = "tasks/page")
    public PageInfo<ManufactureOrderTaskDTO> queryOrderTasksPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            ManufactureOrderTaskQuery query
    ) {
        var criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return manufactureOrderService.findTasksPage(criteria);
    }

    @Operation(summary = "根据ID查询工单任务详情")
    @GetMapping(value = "tasks/{id}")
    public ManufactureOrderTaskDTO queryTaskById(@PathVariable String id) {
        return manufactureOrderService.findTaskById(id).orElse(null);
    }

    @Operation(summary = "根据ID更新工单任务")
    @PutMapping(value = "tasks/{id}")
    public void updateOrderTask(
            @PathVariable String id,
            @Validated @RequestBody ManufactureOrderTaskRO.Update args
    ) {
        var task = convert.from(args);
        task.setId(id);
        manufactureOrderService.updateOrderTaskById(task);
    }

}

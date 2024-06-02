package com.trionesdev.mes.rest.backend.domains.manufacture.controller.impl;

import com.trionesdev.mes.domain.core.domains.manufacture.service.impl.ManufactureOrderService;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro.ManufactureOrderCreateRO;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro.ManufactureOrderUpdateRO;
import com.trionesdev.mes.rest.backend.domains.manufacture.internal.ManufactureBeBeanConvert;
import com.trionesdev.mes.rest.backend.domains.manufacture.internal.ManufactureConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "生产工单")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = ManufactureConstants.MANUFACTURE_PATH)
public class ManufactureOrderController {
    private final ManufactureBeBeanConvert convert;
    private final ManufactureOrderService manufactureOrderService;

    @Operation(summary = "创建生产工单")
    @PostMapping("orders")
    public void createOrder(@Validated @RequestBody ManufactureOrderCreateRO args) {
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
    public void updateOrderById(@PathVariable String id, @Validated @RequestBody ManufactureOrderUpdateRO args) {
        var order = convert.from(args);
        order.setId(id);
        manufactureOrderService.updateManufactureOrderById(order);
    }

}

package com.trionesdev.mes.rest.backend.domains.supplier.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.core.domains.supplier.dao.criteria.SupplierCriteria;
import com.trionesdev.mes.core.domains.supplier.dao.po.SupplierPO;
import com.trionesdev.mes.core.domains.supplier.service.impl.SupplierService;
import com.trionesdev.mes.core.domains.supplier.dto.SupplierDTO;
import com.trionesdev.mes.rest.backend.domains.supplier.controller.query.SupplierQuery;
import com.trionesdev.mes.rest.backend.domains.supplier.controller.ro.SupplierCreateRO;
import com.trionesdev.mes.rest.backend.domains.supplier.controller.ro.SupplierUpdateRO;
import com.trionesdev.mes.rest.backend.domains.supplier.internal.SupplierBeRestBeanConvert;
import com.trionesdev.mes.rest.backend.domains.supplier.internal.SupplierRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "供应商")
@RequiredArgsConstructor
@RestController
@RequestMapping(SupplierRestConstants.SUPPLIER_PATH)
public class SupplierController {
    private final SupplierBeRestBeanConvert convert;
    private final SupplierService supplierService;

    @Operation(summary = "创建供应商")
    @PostMapping(value = "suppliers")
    public void createSupplier(@Validated @RequestBody SupplierCreateRO args) {
        SupplierPO supplier = convert.from(args);
        supplierService.createSupplier(supplier);
    }

    @Operation(summary = "根据ID删除供应商")
    @DeleteMapping("suppliers/{id}")
    public void deleteSupplierById(@PathVariable String id) {
        supplierService.deleteSupplierById(id);
    }

    @Operation(summary = "根据ID更新供应商")
    @PutMapping("suppliers/{id}")
    public void updateSupplierById(@PathVariable String id, @Validated @RequestBody SupplierUpdateRO args) {
        SupplierPO supplier = convert.from(args);
        supplier.setId(id);
        supplierService.updateSupplierById(supplier);
    }

    @Operation(summary = "根据ID查询供应商")
    @GetMapping("suppliers/{id}")
    public SupplierDTO querySupplierById(@PathVariable String id) {
        return supplierService.querySupplierById(id).orElse(null);
    }

    @Operation(summary = "查询供应商列表")
    @GetMapping("suppliers/list")
    public List<SupplierDTO> querySupplierList(SupplierQuery query) {
        SupplierCriteria criteria = convert.from(query);
        return supplierService.querySupplierList(criteria);
    }

    @Operation(summary = "查询供应商分页列表")
    @GetMapping("suppliers/page")
    public PageInfo<SupplierDTO> querySupplierPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            SupplierQuery query) {
        SupplierCriteria criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return supplierService.querySupplierPage(criteria);
    }

}

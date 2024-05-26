package com.trionesdev.mes.rest.backend.domains.supplier.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.supplier.repository.criteria.SupplierCriteria;
import com.trionesdev.mes.domain.core.domains.supplier.repository.po.SupplierPO;
import com.trionesdev.mes.domain.core.domains.supplier.service.impl.SupplierService;
import com.trionesdev.mes.domain.core.dto.supplier.SupplierDTO;
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

@Tag(name = "供应商")
@RequiredArgsConstructor
@RestController
@RequestMapping(SupplierRestConstants.SUPPLIER_PATH)
public class SupplierController {
    private final SupplierBeRestBeanConvert convert;
    private final SupplierService supplierService;

    @Operation(summary = "创建供应商")
    @PostMapping(value = "suppliers")
    public void createCustomer(@Validated @RequestBody SupplierCreateRO args) {
        SupplierPO supplier = convert.from(args);
        supplierService.createSupplier(supplier);
    }

    @Operation(summary = "根据ID删除供应商")
    @DeleteMapping("suppliers/{id}")
    public void deleteCustomerById(@PathVariable String id) {
        supplierService.deleteSupplierById(id);
    }

    @Operation(summary = "根据ID更新供应商")
    @PutMapping("suppliers/{id}")
    public void updateCustomerById(@PathVariable String id, @Validated @RequestBody SupplierUpdateRO args) {
        SupplierPO supplier = convert.from(args);
        supplier.setId(id);
        supplierService.updateSupplierById(supplier);
    }

    @Operation(summary = "根据ID查询供应商")
    @GetMapping("suppliers/{id}")
    public SupplierDTO queryCustomerById(@PathVariable String id) {
        return supplierService.querySupplierById(id).orElse(null);
    }

    @Operation(summary = "查询供应商分页列表")
    @GetMapping("suppliers/page")
    public PageInfo<SupplierDTO> queryCustomerPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            SupplierQuery query) {
        SupplierCriteria criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return supplierService.queryCustomerPage(criteria);
    }

}

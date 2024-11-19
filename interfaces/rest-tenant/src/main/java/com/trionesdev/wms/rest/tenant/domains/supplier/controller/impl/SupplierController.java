package com.trionesdev.wms.rest.tenant.domains.supplier.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.supplier.dao.po.SupplierPO;
import com.trionesdev.wms.core.domains.supplier.service.impl.SupplierService;
import com.trionesdev.wms.rest.tenant.domains.supplier.controller.ro.SupplierQueryRO;
import com.trionesdev.wms.rest.tenant.domains.supplier.controller.ro.SupplierRO;
import com.trionesdev.wms.rest.tenant.domains.supplier.internal.SupplierBeRestConvert;
import com.trionesdev.wms.rest.tenant.domains.supplier.internal.SupplierRestConstants;
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

@Tag(name = "供应商管理")
@RequiredArgsConstructor
@RestController
@RequestMapping(SupplierRestConstants.SUPPLIER_PATH)
public class SupplierController {
    private final SupplierBeRestConvert convert;
    private final SupplierService supplierService;

    @Operation(summary = "创建供应商")
    @PostMapping("suppliers")
    public void create(@Validated @RequestBody SupplierRO args) {
        var po = convert.from(args);
        supplierService.create(po);
    }

    @Operation(summary = "根据ID更新供应商")
    @PutMapping("suppliers/{id}")
    public void updateById(
            @PathVariable(value = "id") String id,
            @Validated @RequestBody SupplierRO args
    ) {
        var po = convert.from(args);
        po.setId(id);
        supplierService.updateById(po);
    }

    @Operation(summary = "根据ID查询供应商")
    @GetMapping(value = "suppliers/{id}")
    public SupplierPO queryById(@PathVariable String id) {
        return supplierService.findById(id).orElse(null);
    }

    @Operation(summary = "查询供应商列表分页")
    @GetMapping("suppliers/page")
    public PageInfo<SupplierPO> queryPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            SupplierQueryRO query) {
        var criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return supplierService.findPage(criteria);
    }

    @Operation(summary = "查询供应商列表")
    @GetMapping("suppliers/list")
    public List<SupplierPO> queryList(SupplierQueryRO query) {
        var criteria = convert.from(query);
        return supplierService.findList(criteria);
    }

    @Operation(summary = "批量删除供应商")
    @Parameter(name = "ids", description = "以,分割的id字符串，形如：1,2,3")
    @DeleteMapping(value = "suppliers/{ids}")
    public void deleteByIds(@PathVariable(value = "ids") List<String> ids) {
        supplierService.deleteByIds(ids);
    }
}

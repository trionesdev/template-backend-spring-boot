package com.trionesdev.wms.rest.tenant.domains.customer.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.customer.dao.po.CustomerPO;
import com.trionesdev.wms.core.domains.customer.service.impl.CustomerService;
import com.trionesdev.wms.rest.tenant.domains.customer.controller.ro.CustomerQueryRO;
import com.trionesdev.wms.rest.tenant.domains.customer.controller.ro.CustomerRO;
import com.trionesdev.wms.rest.tenant.domains.customer.internal.CustomerBeRestConvert;
import com.trionesdev.wms.rest.tenant.domains.customer.internal.CustomerRestConstants;
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

@Tag(name = "客户管理")
@RequiredArgsConstructor
@RestController
@RequestMapping(CustomerRestConstants.CUSTOMER_PATH)
public class CustomerController {
    private final CustomerBeRestConvert convert;
    private final CustomerService customerService;

    @Operation(summary = "创建客户")
    @PostMapping("customers")
    public void create(@Validated @RequestBody CustomerRO args) {
        var po = convert.from(args);
        customerService.create(po);
    }

    @Operation(summary = "根据ID更新客户")
    @PutMapping("customers/{id}")
    public void updateById(
            @PathVariable(value = "id") String id,
            @Validated @RequestBody CustomerRO args
    ) {
        var po = convert.from(args);
        po.setId(id);
        customerService.updateById(po);
    }

    @Operation(summary = "根据ID查询客户")
    @GetMapping(value = "customers/{id}")
    public CustomerPO queryById(@PathVariable String id) {
        return customerService.findById(id).orElse(null);
    }

    @Operation(summary = "查询客户列表分页")
    @GetMapping("customers/page")
    public PageInfo<CustomerPO> queryPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            CustomerQueryRO query) {
        var criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return customerService.findPage(criteria);
    }

    @Operation(summary = "查询客户列表")
    @GetMapping("customers/list")
    public List<CustomerPO> queryList(CustomerQueryRO query) {
        var criteria = convert.from(query);
        return customerService.findList(criteria);
    }

    @Operation(summary = "批量删除客户")
    @Parameter(name = "ids", description = "以,分割的id字符串，形如：1,2,3")
    @DeleteMapping(value = "customers/{ids}")
    public void deleteByIds(@PathVariable(value = "ids") List<String> ids) {
        customerService.deleteByIds(ids);
    }
}

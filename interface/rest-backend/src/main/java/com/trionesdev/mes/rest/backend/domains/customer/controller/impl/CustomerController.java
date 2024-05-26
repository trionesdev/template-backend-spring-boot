package com.trionesdev.mes.rest.backend.domains.customer.controller.impl;

import com.trionesdev.mes.domain.core.domains.customer.repository.po.CustomerPO;
import com.trionesdev.mes.rest.backend.domains.customer.controller.ro.CustomerUpdateRO;
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

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.customer.repository.criteria.CustomerCriteria;
import com.trionesdev.mes.domain.core.domains.customer.service.impl.CustomerService;
import com.trionesdev.mes.domain.core.dto.customer.CustomerDTO;
import com.trionesdev.mes.rest.backend.domains.customer.controller.query.CustomerQuery;
import com.trionesdev.mes.rest.backend.domains.customer.controller.ro.CustomerCreateRO;
import com.trionesdev.mes.rest.backend.domains.customer.internal.CustomerBeRestBeanConvert;
import com.trionesdev.mes.rest.backend.domains.customer.internal.CustomerRestConstants;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "客户")
@RequiredArgsConstructor
@RestController
@RequestMapping(CustomerRestConstants.CUSTOMER_PATH)
public class CustomerController {
    private final CustomerBeRestBeanConvert convert;
    private final CustomerService customerService;

    @Operation(summary = "创建客户")
    @PostMapping(value = "customers")
    public void createCustomer(@Validated @RequestBody CustomerCreateRO args) {
        CustomerPO customer = convert.from(args);
        customerService.createCustomer(customer);
    }

    @Operation(summary = "根据ID删除客户")
    @DeleteMapping("customers/{id}")
    public void deleteCustomerById(@PathVariable String id) {
        customerService.deleteCustomerById(id);
    }

    @Operation(summary = "根据ID更新客户")
    @PutMapping("customers/{id}")
    public void updateCustomerById(@PathVariable String id, @Validated @RequestBody CustomerUpdateRO args) {
        CustomerPO customer = convert.from(args);
        customer.setId(id);
        customerService.updateCustomerById(customer);
    }

    @Operation(summary = "根据ID查询客户")
    @GetMapping("customers/{id}")
    public CustomerDTO queryCustomerById(@PathVariable String id) {
        return customerService.queryCustomerById(id).orElse(null);
    }

    @Operation(summary = "查询客户分页列表")
    @GetMapping("customers/page")
    public PageInfo<CustomerDTO> queryCustomerPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            CustomerQuery query) {
        CustomerCriteria criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return customerService.queryCustomerPage(criteria);
    }

}

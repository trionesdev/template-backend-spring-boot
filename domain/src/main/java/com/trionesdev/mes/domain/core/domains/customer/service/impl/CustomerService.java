package com.trionesdev.mes.domain.core.domains.customer.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.customer.internal.CustomerBeanConvert;
import com.trionesdev.mes.domain.core.domains.customer.manager.impl.CustomerManager;
import com.trionesdev.mes.domain.core.domains.customer.repository.criteria.CustomerCriteria;
import com.trionesdev.mes.domain.core.domains.customer.repository.po.CustomerPO;
import com.trionesdev.mes.domain.core.dto.customer.CustomerDTO;
import com.trionesdev.mes.domain.core.provider.ssp.custom.impl.CustomProvider;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerBeanConvert convert;
    private final CustomerManager customerManager;
      private final CustomProvider customProvider;

    public void createCustomer(CustomerPO customer) {
        if (StrUtil.isBlank(customer.getCode())) {
            customer.setCode(customProvider.generateCode("CUSTOMER"));
        }
        customerManager.createCustomer(customer);
    }

    public void deleteCustomerById(String id) {
        customerManager.deleteCustomerById(id);
    }

    public void updateCustomerById(CustomerPO customer) {
        customerManager.updateCustomerById(customer);
    }

    public Optional<CustomerDTO> queryCustomerById(String id) {
        return customerManager.findById(id).map(this::assemble);
    }

    public PageInfo<CustomerDTO> queryCustomerPage(CustomerCriteria criteria) {
        PageInfo<CustomerPO> page = customerManager.findPage(criteria);
        return PageUtils.of(page, assembleBatch(page.getRows()));
    }

    private CustomerDTO assemble(CustomerPO record) {
        return convert.poToDto(record);
    }

    private List<CustomerDTO> assembleBatch(List<CustomerPO> records) {
        return records.stream().map(this::assemble).collect(Collectors.toList());
    }

}

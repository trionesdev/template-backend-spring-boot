package com.trionesdev.mes.domain.core.domains.customer.manager.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.customer.repository.criteria.CustomerCriteria;
import com.trionesdev.mes.domain.core.domains.customer.repository.impl.CustomerRepository;
import com.trionesdev.mes.domain.core.domains.customer.repository.po.CustomerPO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerManager {
    private final CustomerRepository customerRepository;

    public void createCustomer(CustomerPO customer) {
        customerRepository.save(customer);
    }

    public void deleteCustomerById(String id) {
        customerRepository.removeById(id);
    }

    public void updateCustomerById(CustomerPO customer) {
        customerRepository.updateById(customer);
    }

    public Optional<CustomerPO> findById(String id) {
        return Optional.ofNullable(customerRepository.getById(id));
    }

    List<CustomerPO> findList(CustomerCriteria criteria) {
        return customerRepository.selectList(criteria);
    }

    public PageInfo<CustomerPO> findPage(CustomerCriteria criteria) {
        return customerRepository.selectPage(criteria);
    }

}

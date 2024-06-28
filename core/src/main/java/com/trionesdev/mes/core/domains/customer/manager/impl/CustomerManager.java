package com.trionesdev.mes.core.domains.customer.manager.impl;

import java.util.List;
import java.util.Optional;

import com.trionesdev.mes.core.domains.customer.dao.criteria.CustomerCriteria;
import com.trionesdev.mes.core.domains.customer.dao.impl.CustomerDAO;
import com.trionesdev.mes.core.domains.customer.dao.po.CustomerPO;
import org.springframework.stereotype.Service;

import com.trionesdev.commons.core.page.PageInfo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerManager {
    private final CustomerDAO customerDAO;

    public void createCustomer(CustomerPO customer) {
        customerDAO.save(customer);
    }

    public void deleteCustomerById(String id) {
        customerDAO.removeById(id);
    }

    public void updateCustomerById(CustomerPO customer) {
        customerDAO.updateById(customer);
    }

    public Optional<CustomerPO> findById(String id) {
        return Optional.ofNullable(customerDAO.getById(id));
    }

    List<CustomerPO> findList(CustomerCriteria criteria) {
        return customerDAO.selectList(criteria);
    }

    public PageInfo<CustomerPO> findPage(CustomerCriteria criteria) {
        return customerDAO.selectPage(criteria);
    }

}

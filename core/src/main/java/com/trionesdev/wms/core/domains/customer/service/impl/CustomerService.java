package com.trionesdev.wms.core.domains.customer.service.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.customer.dao.criteria.CustomerCriteria;
import com.trionesdev.wms.core.domains.customer.dao.po.CustomerPO;
import com.trionesdev.wms.core.domains.customer.manager.impl.CustomerManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerManager customerManager;

    public void create(CustomerPO po) {
        customerManager.create(po);
    }

    public void updateById(CustomerPO po) {
        customerManager.updateById(po);
    }

    public Optional<CustomerPO> findById(String id) {
        return customerManager.findById(id);
    }

    public List<CustomerPO> findList(CustomerCriteria criteria) {
        return customerManager.findList(criteria);
    }

    public PageInfo<CustomerPO> findPage(CustomerCriteria criteria) {
        return customerManager.findPage(criteria);
    }

    public void deleteByIds(List<String> ids) {
        customerManager.deleteByIds(ids);
    }
}

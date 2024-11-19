package com.trionesdev.wms.core.domains.customer.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.customer.dao.criteria.CustomerCriteria;
import com.trionesdev.wms.core.domains.customer.dao.impl.CustomerDAO;
import com.trionesdev.wms.core.domains.customer.dao.po.CustomerPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerManager {
    private final CustomerDAO customerRepository;

    public void create(CustomerPO po) {
        customerRepository.save(po);
    }

    public void updateById(CustomerPO po) {
        customerRepository.updateById(po);
    }

    public Optional<CustomerPO> findById(String id) {
        return Optional.ofNullable(customerRepository.getById(id));
    }

    public List<CustomerPO> findList(CustomerCriteria criteria) {
        return customerRepository.selectList(criteria);
    }

    public PageInfo<CustomerPO> findPage(CustomerCriteria criteria) {
        return customerRepository.selectPage(criteria);
    }

    public void deleteByIds(List<String> ids) {
        customerRepository.removeByIds(ids);
    }
}

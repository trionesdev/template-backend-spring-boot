package com.trionesdev.mes.domain.core.domains.supplier.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.supplier.repository.criteria.SupplierCriteria;
import com.trionesdev.mes.domain.core.domains.supplier.repository.impl.SupplierRepository;
import com.trionesdev.mes.domain.core.domains.supplier.repository.po.SupplierPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SupplierManager {
    private final SupplierRepository customerRepository;

    public void createSupplier(SupplierPO supplier) {
        customerRepository.save(supplier);
    }

    public void deleteSupplierById(String id) {
        customerRepository.removeById(id);
    }

    public void updateSupplierById(SupplierPO customer) {
        customerRepository.updateById(customer);
    }

    public Optional<SupplierPO> findById(String id) {
        return Optional.ofNullable(customerRepository.getById(id));
    }

    List<SupplierPO> findList(SupplierCriteria criteria) {
        return customerRepository.selectList(criteria);
    }

    public PageInfo<SupplierPO> findPage(SupplierCriteria criteria) {
        return customerRepository.selectPage(criteria);
    }

}

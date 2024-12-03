package com.trionesdev.wms.core.domains.supplier.manager.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.supplier.dao.criteria.SupplierCriteria;
import com.trionesdev.wms.core.domains.supplier.dao.impl.SupplierDAO;
import com.trionesdev.wms.core.domains.supplier.dao.po.SupplierPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SupplierManager {
    private final SupplierDAO supplierRepository;

    public void create(SupplierPO po) {
        supplierRepository.save(po);
    }

    public void updateById(SupplierPO po) {
        supplierRepository.updateById(po);
    }

    public Optional<SupplierPO> findById(String id) {
        return Optional.ofNullable(supplierRepository.getById(id));
    }

    public List<SupplierPO> findById(List<String> ids) {
        return supplierRepository.findById(ids);
    }

    public List<SupplierPO> findList(SupplierCriteria criteria) {
        return supplierRepository.selectList(criteria);
    }

    public PageInfo<SupplierPO> findPage(SupplierCriteria criteria) {
        return supplierRepository.selectPage(criteria);
    }

    public void deleteByIds(List<String> ids) {
        supplierRepository.removeByIds(ids);
    }
}

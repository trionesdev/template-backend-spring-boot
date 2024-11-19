package com.trionesdev.wms.core.domains.supplier.service.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.supplier.dao.criteria.SupplierCriteria;
import com.trionesdev.wms.core.domains.supplier.dao.po.SupplierPO;
import com.trionesdev.wms.core.domains.supplier.manager.impl.SupplierManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SupplierService {
    private final SupplierManager supplierManager;

    public void create(SupplierPO po) {
        supplierManager.create(po);
    }

    public void updateById(SupplierPO po) {
        supplierManager.updateById(po);
    }

    public Optional<SupplierPO> findById(String id) {
        return supplierManager.findById(id);
    }

    public List<SupplierPO> findList(SupplierCriteria criteria) {
        return supplierManager.findList(criteria);
    }

    public PageInfo<SupplierPO> findPage(SupplierCriteria criteria) {
        return supplierManager.findPage(criteria);
    }

    public void deleteByIds(List<String> ids) {
        supplierManager.deleteByIds(ids);
    }
}

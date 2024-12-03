package com.trionesdev.wms.core.domains.supplier.provider;

import com.trionesdev.wms.core.domains.supplier.dao.po.SupplierPO;
import com.trionesdev.wms.core.domains.supplier.dto.SupplierDTO;
import com.trionesdev.wms.core.domains.supplier.internal.SupplierBeanConvert;
import com.trionesdev.wms.core.domains.supplier.manager.impl.SupplierManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class SupplierProvider {
    private final SupplierManager supplierManager;
    private final SupplierBeanConvert convert;

    public List<SupplierDTO> findById(List<String> ids) {
        List<SupplierPO> list = supplierManager.findById(ids);
        return convert.from(list);
    }
}

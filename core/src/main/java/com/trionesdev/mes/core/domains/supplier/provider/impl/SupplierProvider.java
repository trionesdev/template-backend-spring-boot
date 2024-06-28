package com.trionesdev.mes.core.domains.supplier.provider.impl;

import com.trionesdev.mes.core.domains.supplier.service.impl.SupplierService;
import com.trionesdev.mes.core.domains.supplier.dto.SupplierDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SupplierProvider {
    private final SupplierService supplierService;

    public SupplierDTO querySupplierByCode(String code) {
        return supplierService.querySupplierByCode(code).orElse(null);
    }

}

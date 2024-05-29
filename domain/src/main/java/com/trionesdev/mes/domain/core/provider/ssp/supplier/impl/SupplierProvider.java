package com.trionesdev.mes.domain.core.provider.ssp.supplier.impl;

import com.trionesdev.mes.domain.core.domains.supplier.service.impl.SupplierService;
import com.trionesdev.mes.domain.core.dto.supplier.SupplierDTO;
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

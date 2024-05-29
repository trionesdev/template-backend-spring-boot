package com.trionesdev.mes.domain.core.domains.supplier.service.impl;

import cn.hutool.core.util.StrUtil;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.supplier.internal.SupplierBeanConvert;
import com.trionesdev.mes.domain.core.domains.supplier.manager.impl.SupplierManager;
import com.trionesdev.mes.domain.core.domains.supplier.repository.criteria.SupplierCriteria;
import com.trionesdev.mes.domain.core.domains.supplier.repository.po.SupplierPO;
import com.trionesdev.mes.domain.core.dto.supplier.SupplierDTO;
import com.trionesdev.mes.domain.core.provider.ssp.custom.impl.CustomProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SupplierService {
    private final SupplierBeanConvert convert;
    private final SupplierManager supplierManager;
    private final CustomProvider customProvider;

    public void createSupplier(SupplierPO customer) {
        if (StrUtil.isBlank(customer.getCode())) {
            customer.setCode(customProvider.generateCode("SUPPLIER"));
        }
        supplierManager.createSupplier(customer);
    }

    public void deleteSupplierById(String id) {
        supplierManager.deleteSupplierById(id);
    }

    public void updateSupplierById(SupplierPO customer) {
        supplierManager.updateSupplierById(customer);
    }

    public Optional<SupplierDTO> querySupplierById(String id) {
        return supplierManager.findById(id).map(this::assemble);
    }

    public Optional<SupplierDTO> querySupplierByCode(String code) {
        return supplierManager.findByCode(code).map(this::assemble);
    }

    public List<SupplierDTO> querySupplierList(SupplierCriteria criteria) {
        List<SupplierPO> suppliers = supplierManager.findList(criteria);
        return assembleBatch(suppliers);
    }

    public PageInfo<SupplierDTO> querySupplierPage(SupplierCriteria criteria) {
        PageInfo<SupplierPO> page = supplierManager.findPage(criteria);
        return PageUtils.of(page, assembleBatch(page.getRows()));
    }

    private SupplierDTO assemble(SupplierPO record) {
        return convert.poToDto(record);
    }

    private List<SupplierDTO> assembleBatch(List<SupplierPO> records) {
        return records.stream().map(this::assemble).collect(Collectors.toList());
    }

}

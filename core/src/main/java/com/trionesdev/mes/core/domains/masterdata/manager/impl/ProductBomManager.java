package com.trionesdev.mes.core.domains.masterdata.manager.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.mes.core.domains.masterdata.dao.impl.ProductMaterialDAO;
import com.trionesdev.mes.core.domains.masterdata.entity.ProductBom;
import com.trionesdev.mes.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.core.domains.masterdata.dao.po.ProductMaterialPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductBomManager {
    private final MasterDataBeanConvert convert;
    private final ProductMaterialDAO productMaterialDAO;

    private List<ProductMaterialPO> transformToPo(ProductBom bom) {
        return bom.getMaterials().stream().map(material -> {
            ProductMaterialPO po = convert.entityToPo(material);
            po.setProductCode(bom.getProductCode());
            return po;
        }).collect(Collectors.toList());
    }

    public void createBom(ProductBom bom) {
        List<ProductMaterialPO> materials = transformToPo(bom);
        if (CollectionUtil.isEmpty(materials)) {
            return;
        }
        productMaterialDAO.saveBatch(materials);
    }

    public void deleteBom(String productCode) {
        productMaterialDAO.deleteByProductCode(productCode);
    }

    @Transactional
    public void updateBom(ProductBom bom) {
        productMaterialDAO.deleteByProductCode(bom.getProductCode());
        List<ProductMaterialPO> materials = transformToPo(bom);
        if (CollectionUtil.isEmpty(materials)) {
            return;
        }
        productMaterialDAO.saveBatch(materials);
    }

    public List<ProductMaterialPO> findByProductCode(String productCode) {
        return productMaterialDAO.findByProductCode(productCode);
    }

    public List<ProductMaterialPO> findByProductCodes(Collection<String> productCodes) {
        return productMaterialDAO.findByProductCodes(productCodes);
    }

}

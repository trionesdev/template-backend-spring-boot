package com.trionesdev.mes.core.domains.masterdata.provider.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.trionesdev.mes.core.domains.masterdata.dto.DefectiveDTO;
import com.trionesdev.mes.core.domains.masterdata.dto.ManufactureProcessDTO;
import com.trionesdev.mes.core.domains.masterdata.dto.ProcessPermissionGrantDTO;
import com.trionesdev.mes.core.domains.masterdata.dto.ProductDefinitionDTO;
import com.trionesdev.mes.core.domains.masterdata.dto.UnitDTO;
import com.trionesdev.mes.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.core.domains.masterdata.manager.impl.ManufactureProcessManager;
import com.trionesdev.mes.core.domains.masterdata.service.impl.ProductDefinitionService;
import com.trionesdev.mes.core.domains.masterdata.service.impl.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class MasterDataProvider {
    private final MasterDataBeanConvert convert;
    private final UnitService unitService;
    private final ProductDefinitionService productDefinitionService;
    private final ManufactureProcessManager processManager;

    public List<UnitDTO> getUnitList() {
        return unitService.findList();
    }

    public UnitDTO getUnitById(String id) {
        return unitService.findById(id).orElse(null);
    }

    public List<UnitDTO> getUnitsByIds(Collection<String> ids) {
        if (CollectionUtil.isEmpty(ids)){
            return Collections.emptyList();
        }
        return unitService.findListByIds(ids);
    }

    public ProductDefinitionDTO getProductByCode(String code) {
        return productDefinitionService.findByCode(code).orElse(null);
    }

    public List<ProductDefinitionDTO> getProductsByCodes(Collection<String> codes) {
        if (CollectionUtil.isEmpty(codes)) {
            return Collections.emptyList();
        }
        return productDefinitionService.findProductsByCodes(codes);
    }

    public ManufactureProcessDTO getProcessByCode(String code) {
        return processManager.findByCode(code).map(convert::poToDto).orElse(null);
    }

    public List<ManufactureProcessDTO> getProcessesByCodes(Collection<String> codes) {
        if (CollectionUtil.isEmpty(codes)) {
            return Collections.emptyList();
        }
        return processManager.findListByCodes(codes).stream().map(convert::poToDto).toList();
    }

    /**
     * get process defective options by code
     *
     * @param code
     * @return
     */
    public List<DefectiveDTO> getProcessDefectiveOptionsByCode(String code) {
        return processManager.findDefectiveOptionsByCode(code).stream().map(convert::defectivePoToDto).collect(Collectors.toList());
    }

    public ProcessPermissionGrantDTO getProcessPermissionGrant(String code) {
        return processManager.findByCode(code).map(po -> convert.grantPoToDto(po.getPermissionGrant())).orElse(null);
    }

}

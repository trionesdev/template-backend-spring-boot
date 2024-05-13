package com.trionesdev.mes.domain.core.domains.masterdata.service.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ManufactureBom;
import com.trionesdev.mes.domain.core.domains.masterdata.internal.MasterDataBeanConvert;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.ManufactureBomCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.mapper.ManufactureBomManager;
import com.trionesdev.mes.domain.core.dto.masterdata.ManufactureBomDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ManufactureBomService {
    private final MasterDataBeanConvert convert;
    private final ManufactureBomManager manufactureBomManager;

    public void create(ManufactureBom manufactureBom) {
        manufactureBomManager.create(manufactureBom);
    }

    public void deleteById(String id) {
        manufactureBomManager.deleteById(id);
    }

    public void updateById(ManufactureBom manufactureBom) {
        manufactureBomManager.updateById(manufactureBom);
    }

    public ManufactureBomDTO findById(String id) {
        return manufactureBomManager.findById(id).map(convert::entityToDto).orElse(null);
    }

    public PageInfo<ManufactureBomDTO> findPage(ManufactureBomCriteria criteria) {
        PageInfo<ManufactureBom> page = manufactureBomManager.findPage(criteria);
        return PageUtils.of(page, convert.manufactureBomListEntityToDto(page.getRows()));
    }

}

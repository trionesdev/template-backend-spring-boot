package com.trionesdev.mes.domain.core.domains.masterdata.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ManufactureBomPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.mapper.ManufactureBomMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ManufactureBomRepository extends ServiceImpl<ManufactureBomMapper, ManufactureBomPO> {
}

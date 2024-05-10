package com.trionesdev.mes.domain.core.domains.masterdata.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ManufactureBomItemPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.mapper.ManufactureBomItemMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ManufactureBomItemRepository extends ServiceImpl<ManufactureBomItemMapper, ManufactureBomItemPO> {
}

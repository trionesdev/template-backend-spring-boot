package com.trionesdev.mes.domain.core.domains.manufacture.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.mapper.ManufactureOrderMapper;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderPO;
import org.springframework.stereotype.Repository;

@Repository
public class ManufactureOrderRepository extends ServiceImpl<ManufactureOrderMapper, ManufactureOrderPO> {
}

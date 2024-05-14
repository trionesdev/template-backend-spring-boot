package com.trionesdev.mes.domain.core.domains.manufacture.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.mapper.ManufactureOrderTaskMapper;
import com.trionesdev.mes.domain.core.domains.manufacture.repository.po.ManufactureOrderTaskPO;
import org.springframework.stereotype.Repository;

@Repository
public class ManufactureOrderTaskRepository extends ServiceImpl<ManufactureOrderTaskMapper, ManufactureOrderTaskPO> {
}

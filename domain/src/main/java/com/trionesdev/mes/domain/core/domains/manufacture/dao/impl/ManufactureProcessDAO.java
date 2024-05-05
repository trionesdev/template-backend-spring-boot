package com.trionesdev.mes.domain.core.domains.manufacture.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.manufacture.dao.entity.ManufactureProcess;
import com.trionesdev.mes.domain.core.domains.manufacture.dao.mapper.ManufactureProcessMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ManufactureProcessDAO extends ServiceImpl<ManufactureProcessMapper, ManufactureProcess> {
}

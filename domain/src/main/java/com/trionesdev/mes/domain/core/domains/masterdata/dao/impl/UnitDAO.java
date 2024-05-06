package com.trionesdev.mes.domain.core.domains.masterdata.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.Unit;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.mapper.UnitMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UnitDAO extends ServiceImpl<UnitMapper, Unit> {
}

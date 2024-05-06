package com.trionesdev.mes.domain.core.domains.masterdata.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.Defective;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.mapper.DefectiveMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DefectiveDAO extends ServiceImpl<DefectiveMapper, Defective> {
}

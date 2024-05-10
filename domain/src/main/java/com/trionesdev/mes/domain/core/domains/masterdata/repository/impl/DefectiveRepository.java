package com.trionesdev.mes.domain.core.domains.masterdata.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.DefectivePO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.mapper.DefectiveMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DefectiveRepository extends ServiceImpl<DefectiveMapper, DefectivePO> {
}

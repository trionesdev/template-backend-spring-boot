package com.trionesdev.mes.domain.core.domains.dic.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.dic.dao.entity.Defective;
import com.trionesdev.mes.domain.core.domains.dic.dao.mapper.DefectiveMapper;
import org.springframework.stereotype.Repository;

@Repository
public class DefectiveDAO extends ServiceImpl<DefectiveMapper, Defective> {
}

package com.trionesdev.mes.domain.core.domains.masterdata.dao.impl;

import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.Technology;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.mapper.TechnologyMapper;

@Repository
public class TechnologyDAO extends ServiceImpl<TechnologyMapper, Technology> {

}

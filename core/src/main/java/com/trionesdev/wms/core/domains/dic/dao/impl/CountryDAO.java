package com.trionesdev.wms.core.domains.dic.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.dic.dao.mapper.CountryMapper;
import com.trionesdev.wms.core.domains.dic.dao.po.CountryPO;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDAO extends ServiceImpl<CountryMapper, CountryPO> {
}

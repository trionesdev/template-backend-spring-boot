package com.trionesdev.mes.domain.core.domains.dic.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.dic.dao.entity.ProductDefinition;
import com.trionesdev.mes.domain.core.domains.dic.dao.mapper.ProductDefinitionMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDefinitionDAO extends ServiceImpl<ProductDefinitionMapper, ProductDefinition> {
}

package com.trionesdev.wms.core.domains.dic.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.dic.dao.mapper.DictionaryMapper;
import com.trionesdev.wms.core.domains.dic.dao.po.DictionaryPO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DictionaryDAO extends ServiceImpl<DictionaryMapper, DictionaryPO> {

    public List<DictionaryPO> selectListByTypeCode(String typeCode) {
        return lambdaQuery().eq(DictionaryPO::getTypeCode, typeCode).list();
    }

}

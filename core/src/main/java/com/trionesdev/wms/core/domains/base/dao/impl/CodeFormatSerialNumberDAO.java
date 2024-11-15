package com.trionesdev.wms.core.domains.base.dao.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.wms.core.domains.base.dao.mapper.CodeFormatSerialNumberMapper;
import com.trionesdev.wms.core.domains.base.dao.po.CodeFormatSerialNumberPO;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class CodeFormatSerialNumberDAO extends ServiceImpl<CodeFormatSerialNumberMapper, CodeFormatSerialNumberPO> {

    public CodeFormatSerialNumberPO selectByUniqueIdentifier(String identifier, String timeIdentifier) {
        return lambdaQuery()
                .eq(CodeFormatSerialNumberPO::getIdentifier, identifier)
                .eq(CodeFormatSerialNumberPO::getTimeIdentifier, timeIdentifier)
                .one();
    }

    public void updateByUniqueIdentifier(CodeFormatSerialNumberPO po) {
        Objects.requireNonNull(po.getIdentifier());
        Objects.requireNonNull(po.getTimeIdentifier());
        Objects.requireNonNull(po.getSerialNumber());
        lambdaUpdate()
                .eq(CodeFormatSerialNumberPO::getIdentifier, po.getIdentifier())
                .eq(CodeFormatSerialNumberPO::getTimeIdentifier, po.getTimeIdentifier())
                .set(CodeFormatSerialNumberPO::getSerialNumber, po.getSerialNumber())
                .update();
    }

}

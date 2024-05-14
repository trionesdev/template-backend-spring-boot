package com.trionesdev.mes.domain.core.domains.custom.repository.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trionesdev.mes.domain.core.domains.custom.repository.mapper.CustomCodeSerialNumberMapper;
import com.trionesdev.mes.domain.core.domains.custom.repository.po.CustomCodeSerialNumberPO;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
public class CustomCodeSerialNumberRepository extends ServiceImpl<CustomCodeSerialNumberMapper, CustomCodeSerialNumberPO> {

    public CustomCodeSerialNumberPO selectByTimeIdentifier(String identifier, String timeIdentifier) {
        return lambdaQuery()
                .eq(CustomCodeSerialNumberPO::getIdentifier, identifier)
                .eq(CustomCodeSerialNumberPO::getTimeIdentifier, timeIdentifier)
                .one();
    }

    public void updateByTimeIdentifier(CustomCodeSerialNumberPO po) {
        Objects.requireNonNull(po.getIdentifier());
        Objects.requireNonNull(po.getTimeIdentifier());
        Objects.requireNonNull(po.getSerialNumber());
        lambdaUpdate()
                .eq(CustomCodeSerialNumberPO::getIdentifier, po.getIdentifier())
                .eq(CustomCodeSerialNumberPO::getTimeIdentifier, po.getTimeIdentifier())
                .set(CustomCodeSerialNumberPO::getSerialNumber, po.getSerialNumber())
                .update();
    }

}

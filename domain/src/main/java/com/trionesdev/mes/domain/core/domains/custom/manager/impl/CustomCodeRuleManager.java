package com.trionesdev.mes.domain.core.domains.custom.manager.impl;

import cn.hutool.core.collection.ListUtil;
import com.trionesdev.mes.domain.core.domains.custom.dto.CustomCodeRuleDTO;
import com.trionesdev.mes.domain.core.domains.custom.internal.enums.TimeFormatTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomCodeRuleManager {


    public List<CustomCodeRuleDTO> defaultRules() {
        return ListUtil.of(
                CustomCodeRuleDTO.builder().name("基础数据/产品定义").identifier("PRODUCT_DEFINITION").prefix("PD").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build(),
                CustomCodeRuleDTO.builder().name("基础数据/不良品项").identifier("DEFECTIVE").prefix("BLP").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build()
        );
    }
}

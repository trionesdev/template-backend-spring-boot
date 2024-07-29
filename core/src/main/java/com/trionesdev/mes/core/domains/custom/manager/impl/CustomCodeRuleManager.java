package com.trionesdev.mes.core.domains.custom.manager.impl;

import cn.hutool.core.collection.ListUtil;
import com.trionesdev.commons.exception.NotFoundException;
import com.trionesdev.mes.core.domains.custom.dao.impl.CustomCodeRuleDAO;
import com.trionesdev.mes.core.domains.custom.dao.impl.CustomCodeSerialNumberDAO;
import com.trionesdev.mes.core.domains.custom.dao.po.CustomCodeRulePO;
import com.trionesdev.mes.core.domains.custom.dao.po.CustomCodeSerialNumberPO;
import com.trionesdev.mes.core.domains.custom.internal.aggregate.entity.CustomCodeRule;
import com.trionesdev.mes.core.domains.custom.internal.enums.TimeFormatTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomCodeRuleManager {
    private final CustomCodeRuleDAO customCodeRuleDAO;
    private final CustomCodeSerialNumberDAO customCodeSerialNumberDAO;

    public List<CustomCodeRule> defaultRules() {
        return ListUtil.of(
                CustomCodeRule.builder().name("基础数据/产品定义").identifier("PRODUCT_DEFINITION").prefix("PD").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build(),
                CustomCodeRule.builder().name("基础数据/不良品项").identifier("DEFECTIVE").prefix("BLP").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build(),
                CustomCodeRule.builder().name("基础数据/工序").identifier("MANUFACTURE_PROCESS").prefix("GX").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build(),
                CustomCodeRule.builder().name("基础数据/工艺路线").identifier("PROCESS_FLOW").prefix("GYLX").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build(),
                CustomCodeRule.builder().name("生产管理/工单").identifier("MANUFACTURE_ORDER").prefix("GD").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build(),
                CustomCodeRule.builder().name("仓库管理/仓库").identifier("WAREHOUSE").prefix("CK").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build(),
                CustomCodeRule.builder().name("客户管理/客户").identifier("CUSTOMER").prefix("KH").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build(),
                CustomCodeRule.builder().name("供应商管理/供应商").identifier("SUPPLIER").prefix("GYS").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build()
        );
    }


    public List<CustomCodeRule> findList() {
        Map<String, CustomCodeRulePO> customCodeRulePOMap = customCodeRuleDAO.list().stream().collect(Collectors.toMap(CustomCodeRulePO::getIdentifier, v -> v));
        return defaultRules().stream().peek(rule -> {
            Optional.ofNullable(customCodeRulePOMap.get(rule.getIdentifier())).ifPresent(po -> {
                rule.setPrefix(po.getPrefix());
                rule.setTimeFormatType(po.getTimeFormatType());
                rule.setSerialNumberDigits(po.getSerialNumberDigits());
            });
        }).collect(Collectors.toList());
    }

    public CustomCodeRule findByIdentifier(String identifier) {
        CustomCodeRulePO po = customCodeRuleDAO.selectByIdentifier(identifier);
        if (Objects.nonNull(po)) {
            return CustomCodeRule.builder().identifier(po.getIdentifier()).prefix(po.getPrefix()).timeFormatType(po.getTimeFormatType()).serialNumberDigits(po.getSerialNumberDigits()).build();
        }
        return defaultRules().stream().filter(rule -> rule.getIdentifier().equals(identifier)).findFirst().orElse(null);
    }


    public Integer nextSerialNumber(String identifier, String timeIdentifier) {
        CustomCodeSerialNumberPO serialNumber = customCodeSerialNumberDAO.selectByTimeIdentifier(identifier, timeIdentifier);
        if (Objects.isNull(serialNumber)) {
            CustomCodeSerialNumberPO po = CustomCodeSerialNumberPO.builder().identifier(identifier).timeIdentifier(timeIdentifier).serialNumber(1).build();
            customCodeSerialNumberDAO.save(po);
            return 1;
        } else {
            serialNumber.setSerialNumber(serialNumber.getSerialNumber() + 1);
            customCodeSerialNumberDAO.updateByTimeIdentifier(serialNumber);
            return serialNumber.getSerialNumber();
        }
    }

    public String generateCode(String identifier) {
        CustomCodeRule customCodeRule = this.findByIdentifier(identifier);
        if (Objects.isNull(customCodeRule)) {
            throw new NotFoundException("CUSTOM_CODE_RULE_NOT_FOUND");
        }
        Integer serialNumber = this.nextSerialNumber(identifier, customCodeRule.timeIdentifier());
        return customCodeRule.generateCode(serialNumber);
    }

}

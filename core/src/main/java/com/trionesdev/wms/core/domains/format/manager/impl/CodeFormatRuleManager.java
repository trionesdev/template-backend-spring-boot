package com.trionesdev.wms.core.domains.format.manager.impl;

import cn.hutool.core.collection.ListUtil;
import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.spring.lock.Lock;
import com.trionesdev.wms.core.domains.format.dao.impl.CodeFormatRuleDAO;
import com.trionesdev.wms.core.domains.format.dao.impl.CodeFormatSerialNumberDAO;
import com.trionesdev.wms.core.domains.format.dao.po.CodeFormatSerialNumberPO;
import com.trionesdev.wms.core.domains.format.internal.aggregate.entity.CodeFormatRule;
import com.trionesdev.wms.core.domains.format.internal.enums.TimeFormatTypeEnum;
import com.trionesdev.wms.core.domains.format.repository.impl.CodeFormatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CodeFormatRuleManager {
    private final ActorContext actorContext;
    private final CodeFormatRepository codeFormatRepository;
    private final CodeFormatRuleDAO customCodeRuleDAO;
    private final CodeFormatSerialNumberDAO customCodeSerialNumberDAO;

    public List<CodeFormatRule> defaultRules() {
        return ListUtil.of(
                CodeFormatRule.builder().name("基础数据/产品定义").identifier("PRODUCT_DEFINITION").prefix("PD").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build(),
                CodeFormatRule.builder().name("基础数据/不良品项").identifier("DEFECTIVE").prefix("BLP").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build(),
                CodeFormatRule.builder().name("基础数据/工序").identifier("MANUFACTURE_PROCESS").prefix("GX").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build(),
                CodeFormatRule.builder().name("基础数据/工艺路线").identifier("PROCESS_FLOW").prefix("GYLX").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build(),
                CodeFormatRule.builder().name("生产管理/工单").identifier("MANUFACTURE_ORDER").prefix("GD").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build(),
                CodeFormatRule.builder().name("仓库管理/仓库").identifier("WAREHOUSE").prefix("CK").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build(),
                CodeFormatRule.builder().name("客户管理/客户").identifier("CUSTOMER").prefix("KH").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build(),
                CodeFormatRule.builder().name("供应商管理/供应商").identifier("SUPPLIER").prefix("GYS").timeFormatType(TimeFormatTypeEnum.YYYY_MM_DD).serialNumberDigits(4).build()
        );
    }

    public void create(CodeFormatRule record) {
        codeFormatRepository.create(record);
    }


    public List<CodeFormatRule> findList() {
        return codeFormatRepository.findList();
    }

    public Optional<CodeFormatRule> findByIdentifier(String identifier) {
        return codeFormatRepository.findByIdentifier(identifier);
    }


    public Integer nextSerialNumber(String identifier, String timeIdentifier) {
        CodeFormatSerialNumberPO serialNumber = customCodeSerialNumberDAO.selectByTimeIdentifier(identifier, timeIdentifier);
        if (Objects.isNull(serialNumber)) {
            CodeFormatSerialNumberPO po = CodeFormatSerialNumberPO.builder().identifier(identifier).timeIdentifier(timeIdentifier).serialNumber(1).build();
            customCodeSerialNumberDAO.save(po);
            return 1;
        } else {
            serialNumber.setSerialNumber(serialNumber.getSerialNumber() + 1);
            customCodeSerialNumberDAO.updateByTimeIdentifier(serialNumber);
            return serialNumber.getSerialNumber();
        }
    }

    @Lock(key = "#{identifier}")
    public String generateCode(String identifier) {
        CodeFormatRule customCodeRule = this.findByIdentifier(identifier).orElse(CodeFormatRule.builder().identifier(identifier).prefix(identifier.toLowerCase()).timeFormatType(TimeFormatTypeEnum.YYYY).serialNumberDigits(4).build());
        Integer serialNumber = this.nextSerialNumber(identifier, customCodeRule.timeIdentifier());
        return customCodeRule.generateCode(serialNumber);
    }

}

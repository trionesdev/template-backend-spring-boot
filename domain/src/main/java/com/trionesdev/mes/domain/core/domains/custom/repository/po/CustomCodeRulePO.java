package com.trionesdev.mes.domain.core.domains.custom.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.entity.BaseLogicEntity;
import com.trionesdev.mes.domain.core.domains.custom.internal.enums.TimeFormatTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "custom_code_rule")
public class CustomCodeRulePO extends BaseLogicEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String identifier;
    private String prefix;
    private TimeFormatTypeEnum timeFormatType;
    private Integer serialNumberDigits;

}

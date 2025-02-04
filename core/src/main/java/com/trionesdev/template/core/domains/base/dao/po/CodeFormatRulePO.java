package com.trionesdev.template.core.domains.base.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.entity.BaseLogicEntity;
import com.trionesdev.template.core.domains.base.internal.enums.TimeFormatType;
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
@TableName(value = "triones_base_code_rule")
public class CodeFormatRulePO extends BaseLogicEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String name;
    private String identifier;
    private String prefix;
    private TimeFormatType timeFormatType;
    private Integer serialNumberDigits;
    private String description;
}

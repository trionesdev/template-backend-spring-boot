package com.trionesdev.mes.domain.core.domains.masterdata.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.entity.BaseLogicEntity;
import com.trionesdev.commons.mybatisplus.typehandlers.StringCollectionTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "md_manufacture_process")
public class ManufactureProcess extends BaseLogicEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String code;
    private String name;
    @TableField(typeHandler = StringCollectionTypeHandler.class)
    private List<String> defectiveCodes; //不良品项
}

package com.trionesdev.mes.core.domains.masterdata.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.trionesdev.commons.mybatisplus.po.BaseLogicPO;
import com.trionesdev.commons.mybatisplus.typehandlers.CollectionTypeHandler;
import com.trionesdev.commons.mybatisplus.typehandlers.StringCollectionTypeHandler;
import com.trionesdev.mes.core.domains.masterdata.dto.ManufactureProcessDTO;
import com.trionesdev.mes.core.domains.masterdata.internal.enums.ProcessGrantObjType;
import com.trionesdev.mes.core.domains.masterdata.internal.enums.ProcessGrantType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "master_data_manufacture_process", autoResultMap = true)
public class ManufactureProcessPO extends BaseLogicPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String code;
    private String name;
    private BigDecimal ratio;
    @TableField(typeHandler = StringCollectionTypeHandler.class)
    private List<String> defectiveCodes; //不良品项
    @TableField(typeHandler = JacksonTypeHandler.class)
    private PermissionGrant permissionGrant;


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PermissionGrant {
        private ProcessGrantType type;
        private List<GrantObj> assignees;
    }


    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GrantObj {
        private String objId;
        private ProcessGrantObjType objType;
    }

}

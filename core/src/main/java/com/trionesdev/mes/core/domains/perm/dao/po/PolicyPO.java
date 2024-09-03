package com.trionesdev.mes.core.domains.perm.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.entity.BaseLogicEntity;
import com.trionesdev.mes.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.mes.core.domains.perm.internal.enums.PolicyGrantObjType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@TableName(value = "perm_policy", autoResultMap = true)
public class PolicyPO extends BaseLogicEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private ClientType clientType;
    /**
     * 对象类型
     */
    private PolicyGrantObjType grantObjType;
    /**
     * 对象ID
     */
    private String grantObjId;
    private String resObj;
    private String resAct;


}

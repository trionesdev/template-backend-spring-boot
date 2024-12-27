package com.trionesdev.template.core.domains.perm.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BasePO;
import com.trionesdev.template.core.domains.perm.internal.enums.ClientType;
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
@TableName(value = "perm_functional_resource_action")
public class FunctionalResourceActionPO extends BasePO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String appIdentifier;
    private ClientType clientType;
    private String objectId;
    private String name;
    private String identifier;
}

package com.trionesdev.wms.core.domains.perm.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BasePO;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.FunctionalResourceType;
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
@TableName(value = "perm_functional_resource")
public class FunctionalResourcePO extends BasePO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String appIdentifier;
    private ClientType clientType;
    private String parentId;
    private FunctionalResourceType type;
    private String group;
    private String name;
    private String identifier;
    private String icon;
    private String description;
    private String apiIdentifier;
    private String routePath;
}

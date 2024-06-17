package com.trionesdev.mes.domain.core.domains.tenant.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BaseLogicPO;
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
@TableName(value = "tenant_tenant", autoResultMap = true)
public class TenantPO extends BaseLogicPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String parentId;
    private String serial;
    private String name;
    private String logo;
    private String description;
}

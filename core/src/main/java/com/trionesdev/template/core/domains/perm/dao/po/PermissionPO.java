package com.trionesdev.template.core.domains.perm.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BasePO;
import com.trionesdev.template.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.template.core.domains.perm.internal.enums.PermissionSubjectType;
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
@TableName(value = "perm_permission", autoResultMap = true)
public class PermissionPO extends BasePO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    /**
     * 应用标识，例如，租户端 tenant,Boss端 boss
     */
    private String appCode;
    private ClientType clientType;
    /**
     * 用户类型
     */
    private PermissionSubjectType subjectType;
    /**
     * 对象ID
     */
    private String subject;
    private String obj;
    private String effect;

}

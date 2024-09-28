package com.trionesdev.wms.core.domains.org.dao.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BaseLogicPO;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@TableName(value = "org_tenant_member", autoResultMap = true)
public class TenantMemberPO extends BaseLogicPO {
    private String id;
    private String tenantId;
    private String userId;
    @TableField(value = "is_master")
    private Boolean master;
    private String username;
    private String encodedPassword;
    private String nickname;
}

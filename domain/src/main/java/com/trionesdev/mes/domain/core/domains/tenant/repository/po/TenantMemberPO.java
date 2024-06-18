package com.trionesdev.mes.domain.core.domains.tenant.repository.po;

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
@TableName(value = "tenant_member", autoResultMap = true)
public class TenantMemberPO extends BaseLogicPO {
    private String id;
    private String tenantId;
    private String userId;
    private String username;
    private String encryptedPassword;
    private String nickname;
}

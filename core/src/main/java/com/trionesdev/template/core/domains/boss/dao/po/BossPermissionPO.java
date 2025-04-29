package com.trionesdev.template.core.domains.boss.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BasePO;
import com.trionesdev.template.core.domains.boss.shared.enums.PermissionSubjectType;
import com.trionesdev.template.core.domains.boss.shared.enums.ClientType;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@TableName(value = BossPermissionPO.TABLE_NAME, autoResultMap = true)
public class BossPermissionPO extends BasePO {
    public static final String TABLE_NAME = "triones_boss_permission";
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private ClientType clientType;
    /**
     * 用户类型
     */
    private PermissionSubjectType subjectType;
    /**
     * 对象ID
     */
    private String subject;
    private String resourceCode;
    private String effect;

}

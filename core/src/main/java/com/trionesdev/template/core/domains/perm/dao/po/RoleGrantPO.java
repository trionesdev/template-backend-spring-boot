package com.trionesdev.template.core.domains.perm.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.base.Objects;
import com.trionesdev.commons.mybatisplus.po.BaseLogicPO;
import com.trionesdev.template.core.domains.perm.internal.enums.RoleSubjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@TableName(value = "perm_role_grant")
public class RoleGrantPO extends BaseLogicPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private RoleSubjectType subjectType;
    private String subject;
    private String roleId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RoleGrantPO roleGrant = (RoleGrantPO) o;
        return subjectType == roleGrant.subjectType && Objects.equal(subject, roleGrant.subject) && Objects.equal(roleId, roleGrant.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), subjectType, subjectType, roleId);
    }

}

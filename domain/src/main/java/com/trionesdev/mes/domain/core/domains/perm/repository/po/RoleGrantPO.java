package com.trionesdev.mes.domain.core.domains.perm.repository.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.google.common.base.Objects;
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
@TableName("perm_role_grant")
public class RoleGrantPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private ObjType objType;
    private String objId;
    private String roleId;

    public enum ObjType {
        USER
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RoleGrantPO roleGrant = (RoleGrantPO) o;
        return objType == roleGrant.objType && Objects.equal(objId, roleGrant.objId) && Objects.equal(roleId, roleGrant.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), objType, objId, roleId);
    }

}

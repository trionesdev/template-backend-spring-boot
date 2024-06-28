package com.trionesdev.mes.core.domains.perm.dao.po;

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
    private ObjType grantObjType;
    private String grantObjId;
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
        return grantObjType == roleGrant.grantObjType && Objects.equal(grantObjId, roleGrant.grantObjId) && Objects.equal(roleId, roleGrant.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), grantObjType, grantObjId, roleId);
    }

}

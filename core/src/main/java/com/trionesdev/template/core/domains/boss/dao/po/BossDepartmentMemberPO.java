package com.trionesdev.template.core.domains.boss.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BaseLogicPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@TableName(value = BossDepartmentMemberPO.TABLE_NAME, autoResultMap = true)
public class BossDepartmentMemberPO extends BaseLogicPO {
    public static final String TABLE_NAME = "triones_boss_department_member";
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * “0” 表示根部门
     */
    private String departmentId;
    private String userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        BossDepartmentMemberPO that = (BossDepartmentMemberPO) o;
        return Objects.equals(departmentId, that.departmentId) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), departmentId, userId);
    }
}

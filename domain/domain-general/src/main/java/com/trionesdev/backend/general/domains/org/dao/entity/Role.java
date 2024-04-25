package com.trionesdev.backend.general.domains.org.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.entity.BaseLogicEntity;
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
@TableName(value = "org_role")
public class Role extends BaseLogicEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private RoleType type;
    private String groupId;
    private String title;
    private String description;
    private Boolean defaultFlag;

    public enum RoleType {
        ROLE,
        ROLE_GROUP
    }
}

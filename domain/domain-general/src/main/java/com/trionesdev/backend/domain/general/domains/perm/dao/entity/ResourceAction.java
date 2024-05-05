package com.trionesdev.backend.domain.general.domains.perm.dao.entity;

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
@TableName(value = "perm_resource_action")
public class ResourceAction extends BaseLogicEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String resourceId;
    private String name;
    private String identifier;
}

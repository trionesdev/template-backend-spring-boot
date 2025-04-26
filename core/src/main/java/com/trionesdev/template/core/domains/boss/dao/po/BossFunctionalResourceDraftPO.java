package com.trionesdev.template.core.domains.boss.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BasePO;
import com.trionesdev.template.core.domains.boss.shared.enums.FunctionalResourceType;
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
@TableName(value = BossFunctionalResourceDraftPO.TABLE_NAME)
public class BossFunctionalResourceDraftPO extends BasePO {
    public static final String TABLE_NAME = "triones_boss_perm_functional_resource_draft";
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String parentId;
    private FunctionalResourceType type;
    private String groupCode;
    private String name;
    private String uniqueCode;
    private String icon;
    private String description;
    private String apiCode;
    private String routePath;
}

package com.trionesdev.template.core.domains.boss.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BaseLogicPO;
import com.trionesdev.commons.mybatisplus.typehandlers.StringCollectionTypeHandler;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@TableName(value = BossDepartmentPO.TABLE_NAME, autoResultMap = true)
public class BossDepartmentPO extends BaseLogicPO {
    public static final String TABLE_NAME = "triones_boss_department";
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String parentId;
    @TableField(typeHandler = StringCollectionTypeHandler.class)
    private List<String> prevIds;
    private String name;
}

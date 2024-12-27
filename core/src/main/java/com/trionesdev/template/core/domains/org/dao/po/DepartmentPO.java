package com.trionesdev.template.core.domains.org.dao.po;

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
@TableName(value = "org_department", autoResultMap = true)
public class DepartmentPO extends BaseLogicPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String parentId;
    @TableField(typeHandler = StringCollectionTypeHandler.class)
    private List<String> prevIds;
    private String name;
}

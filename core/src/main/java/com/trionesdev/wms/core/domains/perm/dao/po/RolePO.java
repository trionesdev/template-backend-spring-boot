package com.trionesdev.wms.core.domains.perm.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BaseLogicPO;
import com.trionesdev.commons.mybatisplus.typehandlers.StringCollectionTypeHandler;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@TableName(value = "perm_role",autoResultMap = true)
public class RolePO extends BaseLogicPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    /**
     * 租户ID，如果当前为非多租户场景，或者是Boss用户的时候，为空，多租户场景时，boss端tenantId 为 "0"
     */
    private String tenantId;
    private String parentId;
    @TableField(typeHandler = StringCollectionTypeHandler.class)
    private List<String> prevIds;
    private String name;
    private String description;
    private Boolean customFlag;
}

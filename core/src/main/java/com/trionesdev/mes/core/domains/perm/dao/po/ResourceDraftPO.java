package com.trionesdev.mes.core.domains.perm.dao.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BaseLogicPO;
import com.trionesdev.commons.mybatisplus.typehandlers.CollectionTypeHandler;
import com.trionesdev.mes.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.mes.core.domains.perm.internal.enums.ResourceType;
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
@TableName(value = "perm_resource_draft", autoResultMap = true)
public class ResourceDraftPO extends BaseLogicPO {
    private String id;
    private String parentId;
    private ClientType clientType;
    private ResourceType type;
    private String name;
    private String identifier;
    @TableField(typeHandler = ActionsTypeHandler.class)
    private List<Action> actions;


    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Action {
        private String name;
        private String identifier;
    }

    public static class ActionsTypeHandler extends CollectionTypeHandler<Action> {
        public ActionsTypeHandler(Class<?> type) {
            super(type);
        }

        protected Class<Action> specificType() {
            return Action.class;
        }
    }

}

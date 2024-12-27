package com.trionesdev.template.core.domains.perm.dao.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BasePO;
import com.trionesdev.commons.mybatisplus.typehandlers.CollectionTypeHandler;
import com.trionesdev.template.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.template.core.domains.perm.internal.enums.FunctionalResourceType;
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
@TableName(value = "perm_functional_resource_draft", autoResultMap = true)
public class FunctionalResourceDraftPO extends BasePO {
    private String id;
    private String parentId;
    /**
     * 应用标识，例如，租户端 tenant,Boss端 boss, 非多租户的场景，为空
     */
    private String appIdentifier;
    private ClientType clientType;
    private FunctionalResourceType type;
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

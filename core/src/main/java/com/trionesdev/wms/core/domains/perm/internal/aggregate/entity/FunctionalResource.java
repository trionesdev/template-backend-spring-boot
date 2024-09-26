package com.trionesdev.wms.core.domains.perm.internal.aggregate.entity;

import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.commons.exception.TrionesError;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.internal.enums.FunctionalResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class FunctionalResource {
    private String id;
    private String appCode;
    private ClientType clientType;
    private String parentId;
    private FunctionalResourceType type;
    private String groupCoe;
    private String name;
    private String uniqueCode;
    private String icon;
    private String description;
    private String apiCode;
    private String routePath;


    private List<Action> actions;


    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Action {
        private String name;
        private String identifier;
    }

    /**
     * 初始化数据
     *
     * @param parent
     */
    public void initialize(FunctionalResource parent) {
        if (Objects.nonNull(parent)) {
            if (StringUtils.isNotBlank(parent.getGroupCoe())) {
                this.groupCoe = parent.getGroupCoe();
            }
            if (Objects.equals(FunctionalResourceType.GROUP, type)) {
                throw new BusinessException(TrionesError.builder().code("TYPE_ERROR").message("分组类型只能是根数据").build());
            }
            if (Objects.equals(FunctionalResourceType.MENU, type) && !CollectionUtils.containsAny(List.of(FunctionalResourceType.GROUP, FunctionalResourceType.MENU), parent.getType())) {
                throw new BusinessException(TrionesError.builder().code("TYPE_ERROR").message("菜单类型的父级类型只能是分组类型或菜单类型").build());
            }
            if (Objects.equals(FunctionalResourceType.RESOURCE, type) && !CollectionUtils.containsAny(List.of(FunctionalResourceType.GROUP, FunctionalResourceType.MENU), parent.getType())) {
                throw new BusinessException(TrionesError.builder().code("TYPE_ERROR").message("资源类型的父级类型只能是分组类型或菜单类型").build());
            }
            if (Objects.equals(FunctionalResourceType.ACTION, type) && !CollectionUtils.containsAny(List.of(FunctionalResourceType.GROUP, FunctionalResourceType.MENU, FunctionalResourceType.RESOURCE), parent.getType())) {
                throw new BusinessException(TrionesError.builder().code("TYPE_ERROR").message("操作类型的父级类型只能是分组类型或菜单类型或资源类型").build());
            }
        } else {
            if (Objects.equals(FunctionalResourceType.GROUP, type)) {
                this.groupCoe = this.uniqueCode;
            }
        }
    }

    public void validate() {

    }

}

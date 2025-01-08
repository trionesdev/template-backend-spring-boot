package com.trionesdev.template.core.domains.perm.internal;

import com.trionesdev.commons.exception.TrionesError;

public class PermError {
    public static final TrionesError PARENT_RESOURCE_NOT_FOUND = TrionesError.builder().code("PARENT_RESOURCE_NOT_FOUND").message("父级未找到").build();
    public static final TrionesError CAN_NOT_DELETE_ROLE_HAS_SUB = TrionesError.builder().code("CAN_NOT_DELETE_ROLE_HAS_SUB").message("无法删除有子角色的角色").build();
    public static final TrionesError ROLE_SELF_PARENT_INVALID = TrionesError.builder().code("ROLE_SELF_PARENT_INVALID").message("不能设置自己为自己的上级").build();
    public static final TrionesError TENANT_MEMBER_NOT_FOUND = TrionesError.builder().code("TENANT_MEMBER_NOT_FOUND").message("成员未找到").build();
}

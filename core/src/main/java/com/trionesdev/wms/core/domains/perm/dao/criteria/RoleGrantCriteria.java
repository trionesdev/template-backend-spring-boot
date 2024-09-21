package com.trionesdev.wms.core.domains.perm.dao.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import com.trionesdev.wms.core.domains.perm.internal.enums.RoleGrantObjType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoleGrantCriteria extends PageCriteria {
    private String roleId;
    private RoleGrantObjType grantObjType;
    private String grantObjId;
}

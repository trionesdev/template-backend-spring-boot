package com.trionesdev.template.core.domains.perm.dao.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import com.trionesdev.template.core.domains.perm.internal.enums.RoleSubjectType;
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
    private RoleSubjectType subjectType;
    private String subject;
}

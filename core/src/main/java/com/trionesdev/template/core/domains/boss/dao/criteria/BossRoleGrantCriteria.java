package com.trionesdev.template.core.domains.boss.dao.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import com.trionesdev.template.core.domains.boss.shared.enums.RoleSubjectType;
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
public class BossRoleGrantCriteria extends PageCriteria {
    private String roleId;
    private RoleSubjectType subjectType;
    private String subject;
}

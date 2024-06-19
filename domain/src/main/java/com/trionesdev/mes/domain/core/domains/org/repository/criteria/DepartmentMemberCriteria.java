package com.trionesdev.mes.domain.core.domains.org.repository.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
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
public class DepartmentMemberCriteria extends PageCriteria {
    private String departmentId;
    private String memberId;
}

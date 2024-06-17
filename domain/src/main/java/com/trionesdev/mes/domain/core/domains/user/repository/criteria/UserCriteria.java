package com.trionesdev.mes.domain.core.domains.user.repository.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserCriteria extends PageCriteria {
    private String phone;
    private String email;
}

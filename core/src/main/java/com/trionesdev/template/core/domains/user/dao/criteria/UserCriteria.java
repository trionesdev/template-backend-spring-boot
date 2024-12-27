package com.trionesdev.template.core.domains.user.dao.criteria;

import com.trionesdev.commons.core.page.PageCriteria;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserCriteria extends PageCriteria {
    private String phone;
    private String email;
}

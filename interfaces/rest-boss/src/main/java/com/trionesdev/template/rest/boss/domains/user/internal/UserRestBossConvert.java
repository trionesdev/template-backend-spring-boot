package com.trionesdev.template.rest.boss.domains.user.internal;

import com.trionesdev.template.core.domains.user.dao.criteria.UserCriteria;
import com.trionesdev.template.rest.boss.domains.user.controller.ro.UserQueryRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface UserRestBossConvert {
    UserCriteria userCriteriaFromQuery(UserQueryRO userQuery);
}

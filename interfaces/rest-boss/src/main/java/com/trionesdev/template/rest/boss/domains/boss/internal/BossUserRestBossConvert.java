package com.trionesdev.template.rest.boss.domains.boss.internal;

import com.trionesdev.template.core.domains.boss.dao.criteria.BossUserCriteria;
import com.trionesdev.template.core.domains.boss.dto.user.cmd.BossAccountSignInCmd;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.user.AccountSignInRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.user.BossUserQueryRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface BossUserRestBossConvert {
    BossAccountSignInCmd accountSignInCmdFromRo(AccountSignInRO args);

    BossUserCriteria bossUserCriteriaFromQueryRo(BossUserQueryRO args);
}

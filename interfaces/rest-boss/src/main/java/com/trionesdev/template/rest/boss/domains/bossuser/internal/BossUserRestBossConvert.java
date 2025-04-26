package com.trionesdev.template.rest.boss.domains.bossuser.internal;

import com.trionesdev.template.core.domains.bossuser.dao.criteria.BossUserCriteria;
import com.trionesdev.template.core.domains.bossuser.dto.cmd.BossAccountSignInCmd;
import com.trionesdev.template.rest.boss.domains.bossuser.controller.ro.AccountSignInRO;
import com.trionesdev.template.rest.boss.domains.bossuser.controller.ro.BossUserQueryRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface BossUserRestBossConvert {
    BossAccountSignInCmd accountSignInCmdFromRo(AccountSignInRO args);

    BossUserCriteria bossUserCriteriaFromQueryRo(BossUserQueryRO args);
}

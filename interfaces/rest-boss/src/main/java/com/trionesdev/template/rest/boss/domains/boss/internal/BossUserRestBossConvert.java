package com.trionesdev.template.rest.boss.domains.boss.internal;

import com.trionesdev.template.core.domains.boss.dao.criteria.BossUserCriteria;
import com.trionesdev.template.core.domains.boss.dao.po.BossUserPO;
import com.trionesdev.template.core.domains.boss.dto.user.cmd.BossAccountSignInCmd;
import com.trionesdev.template.core.domains.boss.dto.user.cmd.BossUserCreateCmd;
import com.trionesdev.template.core.domains.boss.dto.user.cmd.BossUserUpdateCmd;
import com.trionesdev.template.core.domains.boss.repository.aggregate.entity.BossUser;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.user.*;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface BossUserRestBossConvert {

    BossUserCreateCmd userCreateCmdFromCreateRo(BossUserCreateRO args);

    BossUserUpdateCmd userUpdateCmdFromUpdateRo(BossUserUpdateRO args);
    BossUserUpdateCmd userUpdateCmdFromActorUpdateRo(BossActorProfileUpdateRO args);

    BossAccountSignInCmd accountSignInCmdFromRo(AccountSignInRO args);

    BossUserCriteria bossUserCriteriaFromQueryRo(BossUserQueryRO args);
}

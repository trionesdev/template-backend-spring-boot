package com.trionesdev.template.rest.boss.domains.boss.internal;

import com.trionesdev.template.core.domains.boss.dao.po.BossFunctionalResourceDraftPO;
import com.trionesdev.template.core.domains.boss.dto.perm.cmd.BossPermissionPolicySaveCmd;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.perm.BossFunctionalResourceDraftCreateRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.perm.BossFunctionalResourceDraftUpdateRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.perm.BossPermissionPolicySaveRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface BossPermRestBossConvert {
    BossFunctionalResourceDraftPO functionResPoFromCreateRo(BossFunctionalResourceDraftCreateRO args);

    BossFunctionalResourceDraftPO functionResPoFromUpdateRo(BossFunctionalResourceDraftUpdateRO args);

    BossPermissionPolicySaveCmd policySaveCmdFromRo(BossPermissionPolicySaveRO args);
}

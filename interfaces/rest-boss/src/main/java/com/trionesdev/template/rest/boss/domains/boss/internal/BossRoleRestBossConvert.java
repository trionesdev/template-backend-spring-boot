package com.trionesdev.template.rest.boss.domains.boss.internal;

import com.trionesdev.template.core.domains.boss.dao.po.BossRolePO;
import com.trionesdev.template.core.domains.boss.dto.role.cmd.BossRoleGrantsCreateCmd;
import com.trionesdev.template.core.domains.boss.dto.role.cmd.BossRoleGrantsRemoveCmd;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.role.BossRoleCreateRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.role.BossRoleGrantCreateRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.role.BossRoleGrantRemoveRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.role.BossRoleUpdateRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface BossRoleRestBossConvert {
    BossRolePO rolePoFromCreateRo(BossRoleCreateRO args);

    BossRolePO rolePoFromUpdateRo(BossRoleUpdateRO args);

    BossRoleGrantsCreateCmd roleGrantFromCreateRo(BossRoleGrantCreateRO args);
    BossRoleGrantsRemoveCmd roleGrantFromRemoveCreateRo(BossRoleGrantRemoveRO args);
}

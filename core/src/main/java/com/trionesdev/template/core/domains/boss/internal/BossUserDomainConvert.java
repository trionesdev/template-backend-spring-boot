package com.trionesdev.template.core.domains.boss.internal;

import com.trionesdev.template.core.domains.boss.dao.po.BossUserPO;
import com.trionesdev.template.core.domains.boss.dto.user.BossUserDTO;
import com.trionesdev.template.core.domains.boss.dto.user.cmd.BossUserCreateCmd;
import com.trionesdev.template.core.domains.boss.dto.user.cmd.BossUserUpdateCmd;
import com.trionesdev.template.core.domains.boss.repository.aggregate.entity.BossUser;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface BossUserDomainConvert {

    BossUser userCreateCmdToEntity(BossUserCreateCmd cmd);
    BossUser userUpdateCmdToEntity(BossUserUpdateCmd cmd);

    BossUserPO userEntityToPo(BossUser bossUser);

    BossUser usePoToEntity(BossUserPO bossUser);

    BossUserDTO userEntityToDto(BossUser bossUser);


}

package com.trionesdev.template.core.domains.boss.internal;

import com.trionesdev.template.core.domains.boss.dao.po.BossDepartmentPO;
import com.trionesdev.template.core.domains.boss.dao.po.BossUserPO;
import com.trionesdev.template.core.domains.boss.dto.BossDepartmentDTO;
import com.trionesdev.template.core.domains.boss.dto.BossUserDTO;
import com.trionesdev.template.core.domains.boss.dto.cmd.BossUserCreateCmd;
import com.trionesdev.template.core.domains.boss.dto.cmd.BossUserUpdateCmd;
import com.trionesdev.template.core.domains.boss.repository.aggregate.entity.BossUser;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface BossDomainConvert {

    BossUser userCreateCmdToEntity(BossUserCreateCmd cmd);
    BossUser userUpdateCmdToEntity(BossUserUpdateCmd cmd);

    BossUserPO userEntityToPo(BossUser bossUser);

    BossUser usePoToEntity(BossUserPO bossUser);

    BossUserDTO userEntityToDto(BossUser bossUser);


    BossDepartmentDTO departmentPoToDto(BossDepartmentPO bossDepartmentPO);
}

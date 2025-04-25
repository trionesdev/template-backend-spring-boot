package com.trionesdev.template.core.domains.bossuser.internal;

import com.trionesdev.template.core.domains.bossuser.dao.po.BossUserPO;
import com.trionesdev.template.core.domains.bossuser.repository.aggregate.entity.BossUser;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface BossUserDomainConvert {

    BossUserPO userEntityToPo(BossUser bossUser);
}

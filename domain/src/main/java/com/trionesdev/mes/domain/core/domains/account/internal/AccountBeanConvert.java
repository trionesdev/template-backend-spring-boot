package com.trionesdev.mes.domain.core.domains.account.internal;

import com.trionesdev.mes.domain.core.dto.account.ActorDTO;
import com.trionesdev.mes.domain.core.dto.user.UserDTO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("accountBeanConvert")
public interface AccountBeanConvert {

    ActorDTO userToActor(UserDTO userDTO);

}

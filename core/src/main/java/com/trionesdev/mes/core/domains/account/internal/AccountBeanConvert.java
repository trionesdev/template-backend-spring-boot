package com.trionesdev.mes.core.domains.account.internal;

import com.trionesdev.mes.core.domains.account.dto.ActorDTO;
import com.trionesdev.mes.core.domains.user.dto.UserDTO;
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

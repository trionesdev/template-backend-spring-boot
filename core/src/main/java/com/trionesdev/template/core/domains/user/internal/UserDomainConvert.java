package com.trionesdev.template.core.domains.user.internal;

import com.trionesdev.template.core.domains.user.dao.po.UserPO;
import com.trionesdev.template.core.domains.user.dto.cmd.PhoneBindUserCmd;
import com.trionesdev.template.core.domains.user.dto.cmd.UserCreateCmd;
import com.trionesdev.template.core.domains.user.dto.UserDTO;
import com.trionesdev.template.core.domains.user.internal.entity.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
public interface UserDomainConvert {

    User from(UserCreateCmd userCreateDTO);
    User userCreateCmdToEntity(UserCreateCmd userCreateDTO);

    User from(PhoneBindUserCmd userBindDTO);

    User userPoToEntity(UserPO userPO);

    UserPO entityToPO(User user);

    UserDTO userPoToDTO(UserPO userPO);
    UserDTO userEntityToDTO(User userPO);
}

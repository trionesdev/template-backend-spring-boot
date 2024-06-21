package com.trionesdev.mes.domain.core.domains.user.internal;

import com.trionesdev.mes.domain.core.domains.user.entity.User;
import com.trionesdev.mes.domain.core.domains.user.repository.po.UserPO;
import com.trionesdev.mes.domain.core.dto.user.UserBindDTO;
import com.trionesdev.mes.domain.core.dto.user.UserCreateDTO;
import com.trionesdev.mes.domain.core.dto.user.UserDTO;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("userBeanConvert")
public interface UserBeanConvert {

    User from(UserCreateDTO userCreateDTO);

    User from(UserBindDTO userBindDTO);

    UserPO entityToPO(User user);

    UserDTO userPoToDTO(UserPO userPO);
}

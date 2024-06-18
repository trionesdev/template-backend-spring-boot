package com.trionesdev.mes.domain.core.domains.user.internal;

import com.trionesdev.mes.domain.core.domains.user.entity.User;
import com.trionesdev.mes.domain.core.domains.user.repository.po.UserPO;
import com.trionesdev.mes.domain.core.dto.user.UserBindDTO;
import com.trionesdev.mes.domain.core.dto.user.UserCreateDTO;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true))
@Named("userBeanConvert")
public interface UserBeanConvert {

    User from(UserCreateDTO userCreateDTO);

    User from(UserBindDTO userBindDTO);

    @Mapping(source = "encryptedPassword", target = "password")
    UserPO entityToPO(User user);

}

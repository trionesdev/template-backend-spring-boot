package com.trionesdev.mes.domain.core.provider.ssp.user.impl;

import com.trionesdev.mes.domain.core.domains.user.internal.UserBeanConvert;
import com.trionesdev.mes.domain.core.domains.user.service.UserService;
import com.trionesdev.mes.domain.core.dto.user.UserBindDTO;
import com.trionesdev.mes.domain.core.provider.ssp.user.UserProvider;
import com.trionesdev.mes.domain.core.dto.user.UserCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserProviderLocal implements UserProvider {
    private final UserBeanConvert convert;
    private final UserService userService;

    @Override
    public String createUser(UserCreateDTO user) {
        var userPO = convert.from(user);
        return userService.createUser(userPO);
    }

    @Override
    public String bindUser(UserBindDTO user) {
        var userPO = convert.from(user);
        return userService.bindUser(userPO);
    }
}

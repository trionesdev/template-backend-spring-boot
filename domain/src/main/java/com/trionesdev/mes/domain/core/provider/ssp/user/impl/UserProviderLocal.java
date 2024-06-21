package com.trionesdev.mes.domain.core.provider.ssp.user.impl;

import com.trionesdev.mes.domain.core.domains.user.internal.UserBeanConvert;
import com.trionesdev.mes.domain.core.domains.user.service.UserService;
import com.trionesdev.mes.domain.core.dto.user.AccountSignInArg;
import com.trionesdev.mes.domain.core.dto.user.UserBindDTO;
import com.trionesdev.mes.domain.core.dto.user.UserDTO;
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

    @Override
    public UserDTO getUserById(String id) {
        return userService.findUserById(id).orElse(null);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return userService.findUserByUsername(username).orElse(null);
    }

    @Override
    public UserDTO getUserByPhone(String phone) {
        return userService.findUserByPhone(phone).orElse(null);
    }

    @Override
    public UserDTO accountSignIn(AccountSignInArg args) {
        return userService.accountSignIn(args);
    }
}

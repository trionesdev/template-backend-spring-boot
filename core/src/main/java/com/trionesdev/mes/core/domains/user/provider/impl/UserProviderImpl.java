package com.trionesdev.mes.core.domains.user.provider.impl;

import com.trionesdev.mes.core.domains.user.dto.AccountSignInCmd;
import com.trionesdev.mes.core.domains.user.dto.UserBindCmd;
import com.trionesdev.mes.core.domains.user.dto.UserCreateCmd;
import com.trionesdev.mes.core.domains.user.dto.UserDTO;
import com.trionesdev.mes.core.domains.user.internal.UserBeanConvert;
import com.trionesdev.mes.core.domains.user.internal.entity.User;
import com.trionesdev.mes.core.domains.user.manager.impl.UserManager;
import com.trionesdev.mes.core.domains.user.provider.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserProviderImpl implements UserProvider {
    private final UserBeanConvert convert;
    private final UserManager userManager;

    @Override
    public String createUser(UserCreateCmd user) {
        var userEntity = convert.from(user);
        return userManager.createUser(userEntity);
    }

    @Override
    public String bindUser(UserBindCmd record) {
        var user = convert.from(record);
        return userManager.bindUser(user);
    }

    @Override
    public UserDTO getUserById(String id) {
        return userManager.findUserById(id).map(convert::userEntityToDTO).orElse(null);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return userManager.findUserByUsername(username).map(convert::userEntityToDTO).orElse(null);
    }

    @Override
    public UserDTO getUserByPhone(String phone) {
        return userManager.findUserByPhone(phone).map(convert::userEntityToDTO).orElse(null);
    }

    @Override
    public UserDTO accountSignIn(AccountSignInCmd args) {
        return userManager.findUserByAccount(User.builder().account(args.getAccount()).password(args.getPassword()).build()).map(convert::userEntityToDTO).orElse(null);
    }
}

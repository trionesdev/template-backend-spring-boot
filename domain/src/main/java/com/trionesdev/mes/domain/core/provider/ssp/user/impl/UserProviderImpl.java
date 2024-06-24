package com.trionesdev.mes.domain.core.provider.ssp.user.impl;

import com.trionesdev.mes.domain.core.domains.user.internal.UserBeanConvert;
import com.trionesdev.mes.domain.core.domains.user.manager.impl.UserManager;
import com.trionesdev.mes.domain.core.domains.user.service.UserDomainService;
import com.trionesdev.mes.domain.core.dto.user.AccountSignInArg;
import com.trionesdev.mes.domain.core.dto.user.UserBindDTO;
import com.trionesdev.mes.domain.core.dto.user.UserDTO;
import com.trionesdev.mes.domain.core.provider.ssp.user.UserProvider;
import com.trionesdev.mes.domain.core.dto.user.UserCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserProviderImpl implements UserProvider {
    private final UserBeanConvert convert;
    private final UserManager userManager;
//    private final UserDomainService userService;

    @Override
    public String createUser(UserCreateDTO user) {
        var userEntity = convert.from(user);
        return userManager.createUser(userEntity);
    }

    @Override
    public String bindUser(UserBindDTO record) {
        var user = convert.from(record);
        return userManager.bindUser(user);
    }

    @Override
    public UserDTO getUserById(String id) {
        return userManager.findUserById(id).map(convert::userPoToDTO).orElse(null);
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return userManager.findUserByUsername(username).map(convert::userPoToDTO).orElse(null);
    }

    @Override
    public UserDTO getUserByPhone(String phone) {
        return userManager.findUserByPhone(phone).map(convert::userPoToDTO).orElse(null);
    }

    @Override
    public UserDTO accountSignIn(AccountSignInArg args) {
        return userService.accountSignIn(args);
    }
}

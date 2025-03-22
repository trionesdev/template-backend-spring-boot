package com.trionesdev.template.core.domains.user.provider.impl;

import com.trionesdev.template.core.domains.user.dto.AccountSignInCmd;
import com.trionesdev.template.core.domains.user.dto.PhoneBindUserCmd;
import com.trionesdev.template.core.domains.user.dto.UserCreateCmd;
import com.trionesdev.template.core.domains.user.dto.UserDTO;
import com.trionesdev.template.core.domains.user.internal.UserDomainConvert;
import com.trionesdev.template.core.domains.user.internal.entity.User;
import com.trionesdev.template.core.domains.user.manager.impl.UserManager;
import com.trionesdev.template.core.domains.user.provider.UserProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserProviderImpl implements UserProvider {
    private final UserDomainConvert convert;
    private final UserManager userManager;

    @Override
    public String createUser(UserCreateCmd user) {
        var userEntity = convert.from(user);
        return userManager.createUser(userEntity);
    }

    @Override
    public UserDTO createUserByPhoneOrReturnExist(UserCreateCmd cmd) {
        var user = convert.userCreateCmdToEntity(cmd);
        return convert.userEntityToDTO(userManager.createUserByPhoneOrReturnExist(user));
    }

    @Override
    public String bindUserByPhone(PhoneBindUserCmd record) {
        var user = convert.from(record);
        return userManager.bindUserByPhone(user);
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

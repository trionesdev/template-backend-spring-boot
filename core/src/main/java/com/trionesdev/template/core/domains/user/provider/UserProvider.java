package com.trionesdev.template.core.domains.user.provider;

import com.trionesdev.template.core.domains.user.dto.cmd.AccountSignInCmd;
import com.trionesdev.template.core.domains.user.dto.cmd.PhoneBindUserCmd;
import com.trionesdev.template.core.domains.user.dto.cmd.UserCreateCmd;
import com.trionesdev.template.core.domains.user.dto.UserDTO;

public interface UserProvider {
    String createUser(UserCreateCmd user);

    UserDTO createUserByPhoneOrReturnExist(UserCreateCmd cmd);

    String bindUserByPhone(PhoneBindUserCmd user);

    UserDTO getUserById(String id);

    UserDTO getUserByUsername(String username);

    UserDTO getUserByPhone(String phone);

    UserDTO accountSignIn(AccountSignInCmd args);
}

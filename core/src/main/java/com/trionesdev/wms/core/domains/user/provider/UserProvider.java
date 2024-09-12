package com.trionesdev.wms.core.domains.user.provider;

import com.trionesdev.wms.core.domains.user.dto.AccountSignInCmd;
import com.trionesdev.wms.core.domains.user.dto.UserBindCmd;
import com.trionesdev.wms.core.domains.user.dto.UserCreateCmd;
import com.trionesdev.wms.core.domains.user.dto.UserDTO;

public interface UserProvider {
    String createUser(UserCreateCmd user);

    String bindUser(UserBindCmd user);

    UserDTO getUserById(String id);

    UserDTO getUserByUsername(String username);

    UserDTO getUserByPhone(String phone);

    UserDTO accountSignIn(AccountSignInCmd args);
}

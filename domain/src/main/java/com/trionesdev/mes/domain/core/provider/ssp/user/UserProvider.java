package com.trionesdev.mes.domain.core.provider.ssp.user;

import com.trionesdev.mes.domain.core.dto.user.AccountSignInArg;
import com.trionesdev.mes.domain.core.dto.user.UserBindDTO;
import com.trionesdev.mes.domain.core.dto.user.UserCreateDTO;
import com.trionesdev.mes.domain.core.dto.user.UserDTO;

public interface UserProvider {
    String createUser(UserCreateDTO user);

    String bindUser(UserBindDTO user);

    UserDTO getUserById(String id);

    UserDTO getUserByUsername(String username);

    UserDTO getUserByPhone(String phone);

    UserDTO accountSignIn(AccountSignInArg args);
}

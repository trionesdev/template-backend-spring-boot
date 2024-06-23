package com.trionesdev.mes.domain.core.domains.user.service;

import com.trionesdev.mes.domain.core.domains.user.entity.User;
import com.trionesdev.mes.domain.core.domains.user.repository.po.UserPO;
import com.trionesdev.mes.domain.core.dto.user.AccountSignInArg;
import com.trionesdev.mes.domain.core.dto.user.UserDTO;

import java.util.Optional;

public interface UserDomainService {

    String createUser(User user);

    void deleteUserById(String id);

    void updateUserById(UserPO user);

    Optional<UserDTO> findUserById(String id);

    Optional<UserDTO> findUserByUsername(String username);

    Optional<UserDTO> findUserByPhone(String phone);

    String bindUser(User user);

    UserDTO accountSignIn(AccountSignInArg args);
}

package com.trionesdev.mes.domain.core.domains.user.service;

import com.trionesdev.mes.domain.core.domains.user.entity.User;
import com.trionesdev.mes.domain.core.domains.user.repository.po.UserPO;
import com.trionesdev.mes.domain.core.domains.user.service.bo.AccountSignInArg;

import java.util.Optional;

public interface UserService {

    String createUser(User user);

    void deleteUserById(String id);

    void updateUserById(UserPO user);

    Optional<UserPO> findUserById(String id);

    String bindUser(User user);

    String accountSignIn(AccountSignInArg args);

}

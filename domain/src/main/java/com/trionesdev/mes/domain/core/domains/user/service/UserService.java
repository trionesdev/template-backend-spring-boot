package com.trionesdev.mes.domain.core.domains.user.service;

import com.trionesdev.mes.domain.core.domains.user.repository.po.UserPO;

import java.util.Optional;

public interface UserService {

    void createUser(UserPO user);

    void deleteUserById(String id);

    void updateUserById(UserPO user);

    Optional<UserPO> findUserById(String id);

}

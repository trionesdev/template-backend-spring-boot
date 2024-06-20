package com.trionesdev.mes.domain.core.domains.user.manager.impl;

import com.trionesdev.mes.domain.core.domains.user.repository.impl.UserRepository;
import com.trionesdev.mes.domain.core.domains.user.repository.po.UserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserManager {
    private final UserRepository userRepository;

    public void createUser(UserPO user) {
        userRepository.save(user);
    }

    public void deleteUserById(String id) {
        userRepository.removeById(id);
    }

    public void updateUserById(UserPO user) {
        userRepository.updateById(user);
    }

    public Optional<UserPO> findUserById(String id) {
        return Optional.ofNullable(userRepository.getById(id));
    }

    public Optional<UserPO> findUserByUsername(String username) {
        return Optional.ofNullable(userRepository.selectByUsername(username));
    }

    public Optional<UserPO> findUserByPhone(String phone) {
        return Optional.ofNullable(userRepository.selectByPhone(phone));
    }

}

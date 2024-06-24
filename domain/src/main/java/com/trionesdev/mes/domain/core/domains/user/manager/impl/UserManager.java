package com.trionesdev.mes.domain.core.domains.user.manager.impl;

import com.trionesdev.commons.exception.DuplicatedException;
import com.trionesdev.mes.domain.core.domains.user.entity.User;
import com.trionesdev.mes.domain.core.domains.user.internal.UserBeanConvert;
import com.trionesdev.mes.domain.core.domains.user.repository.impl.UserRepository;
import com.trionesdev.mes.domain.core.domains.user.repository.po.UserPO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserManager {
    private final UserBeanConvert convert;
    private final UserRepository userRepository;

    public String createUser(User user) {
        var userPhoneSnap = userRepository.selectByPhone(user.getPhone());
        if (Objects.nonNull(userPhoneSnap)) {
            throw new DuplicatedException("PHONE_EXISTS");
        }
        var userPo = convert.entityToPO(user);
        userRepository.save(userPo);
        return userPo.getId();
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

    public String bindUser(User user) {
        return Optional.ofNullable(userRepository.selectByPhone(user.getPhone()))
                .map(UserPO::getId)
                .orElseGet(() -> {
                    var userPo = convert.entityToPO(user);
                    userRepository.save(userPo);
                    return userPo.getId();
                });

    }

}

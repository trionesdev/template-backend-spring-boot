package com.trionesdev.template.core.domains.user.manager.impl;

import com.trionesdev.commons.exception.DuplicatedException;
import com.trionesdev.template.core.domains.user.internal.entity.User;
import com.trionesdev.template.core.domains.user.internal.entity.User.AccountType;
import com.trionesdev.template.core.domains.user.repository.impl.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserManager {
    private final UserRepository userRepository;

    public String createUser(User user) {
        var userPhoneSnap = userRepository.findByPhone(user.getPhone());
        if (Objects.nonNull(userPhoneSnap)) {
            throw new DuplicatedException("PHONE_EXISTS");
        }
        return userRepository.save(user);
    }

    public void deleteUserById(String id) {
        userRepository.removeById(id);
    }

    public void updateUserById(User user) {
        userRepository.updateById(user);
    }

    public Optional<User> findUserById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findUserByPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

    public String bindUser(User user) {
        return userRepository.findByPhone(user.getPhone())
                .map(User::getId)
                .orElseGet(() -> userRepository.save(user));
    }

    public Optional<User> findUserByAccount(User user) {
        Optional<User> userSnap;
        if (Objects.equals(AccountType.PHONE, user.getAccountType())) {
            userSnap = userRepository.findByPhone(user.getAccount());
        } else {
            userSnap = userRepository.findByUsername(user.getAccount());
        }
        return userSnap.filter(userPO -> user.passwordMatch(userPO.getEncodedPassword()));
    }

}

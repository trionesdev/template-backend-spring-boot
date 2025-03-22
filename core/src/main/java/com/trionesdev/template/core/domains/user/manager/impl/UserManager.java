package com.trionesdev.template.core.domains.user.manager.impl;

import com.trionesdev.commons.exception.DuplicatedException;
import com.trionesdev.template.core.domains.user.internal.entity.User;
import com.trionesdev.template.core.domains.user.internal.entity.User.AccountType;
import com.trionesdev.template.core.domains.user.repository.impl.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.trionesdev.template.core.domains.user.internal.UserErrors.*;

@RequiredArgsConstructor
@Service
public class UserManager {
    private final UserRepository userRepository;

    private void checkUserExist(User user) {
        if (StringUtils.isNotBlank(user.getUsername())) {
            userRepository.findByUsername(user.getUsername()).ifPresent(userSnap -> {
                if (!Objects.equals(userSnap.getId(), user.getId())) {
                    throw new DuplicatedException(USERNAME_DUPLICATED);
                }
            });
        }
        if (StringUtils.isNotBlank(user.getPhone())) {
            userRepository.findByPhone(user.getPhone()).ifPresent(userSnap -> {
                if (!Objects.equals(userSnap.getId(), user.getId())) {
                    throw new DuplicatedException(PHONE_DUPLICATED);
                }
            });
        }
        if (StringUtils.isNotBlank(user.getEmail())) {
            userRepository.findByEmail(user.getEmail()).ifPresent(userSnap -> {
                if (!Objects.equals(userSnap.getId(), user.getId())) {
                    throw new DuplicatedException(EMAIL_DUPLICATED);
                }
            });
        }
    }

    public String createUser(User user) {
        checkUserExist(user);
        return userRepository.save(user);
    }

    public User createUserByPhoneOrReturnExist(User user) {
        var userSnap = userRepository.findByPhone(user.getPhone()).orElse(null);
        if (Objects.nonNull(userSnap)) {
            return userSnap;
        }
        userRepository.save(user);
        return user;
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

    public String bindUserByPhone(User user) {
        Objects.requireNonNull(user.getPhone());
        return userRepository.findByPhone(user.getPhone())
                .map(User::getId)
                .orElseGet(() -> {
                    userRepository.save(user);
                    return user.getId();
                });
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

    public void changeTenant(User user, String tenantId) {
        user.changeTenant(tenantId);
        userRepository.updateById(user);
    }

}

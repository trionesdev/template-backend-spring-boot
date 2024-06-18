package com.trionesdev.mes.domain.core.domains.user.service.impl;

import com.trionesdev.commons.exception.DuplicatedException;
import com.trionesdev.mes.domain.core.domains.user.entity.User;
import com.trionesdev.mes.domain.core.domains.user.internal.UserBeanConvert;
import com.trionesdev.mes.domain.core.domains.user.manager.impl.UserManager;
import com.trionesdev.mes.domain.core.domains.user.repository.po.UserPO;
import com.trionesdev.mes.domain.core.domains.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceLocal implements UserService {
    private final UserBeanConvert convert;
    private final UserManager userManager;

    @Override
    public String createUser(User user) {
        var userPhoneSnap = userManager.findUserByPhone(user.getPhone());
        if (userPhoneSnap.isPresent()) {
            throw new DuplicatedException("PHONE_EXISTS");
        }
        var userPo = convert.entityToPO(user);
        userManager.createUser(userPo);
        return userPo.getId();
    }

    @Override
    public void deleteUserById(String id) {
        userManager.deleteUserById(id);
    }

    @Override
    public void updateUserById(UserPO user) {
        userManager.updateUserById(user);
    }

    @Override
    public Optional<UserPO> findUserById(String id) {
        return userManager.findUserById(id);
    }

    @Override
    public String bindUser(User user) {
        return userManager.findUserByPhone(user.getPhone()).map(UserPO::getId)
                .orElseGet(() -> {
                    var userPo = convert.entityToPO(user);
                    userManager.createUser(userPo);
                    return userPo.getId();
                });

    }
}

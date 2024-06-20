package com.trionesdev.mes.domain.core.domains.user.service.impl;

import com.trionesdev.commons.context.actor.ActorRoleEnum;
import com.trionesdev.commons.core.jwt.JwtFacade;
import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.commons.exception.DuplicatedException;
import com.trionesdev.mes.domain.core.domains.user.entity.User;
import com.trionesdev.mes.domain.core.domains.user.internal.UserBeanConvert;
import com.trionesdev.mes.domain.core.domains.user.manager.impl.UserManager;
import com.trionesdev.mes.domain.core.domains.user.repository.po.UserPO;
import com.trionesdev.mes.domain.core.domains.user.service.UserService;
import com.trionesdev.mes.domain.core.domains.user.service.bo.AccountSignInArg;
import com.trionesdev.mes.domain.core.domains.user.service.bo.AccountSignInArg.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceLocal implements UserService {
    private final JwtFacade jwtFacade;
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

    @Override
    public String accountSignIn(AccountSignInArg args) {
        Optional<UserPO> userSnap = Optional.empty();
        if (Objects.equals(AccountSignInArg.AccountType.PHONE, args.getAccountType())) {
            userSnap = userManager.findUserByPhone(args.getAccount());
        } else if (Objects.equals(AccountType.USERNAME, args.getAccountType())) {
            userSnap = userManager.findUserByUsername(args.getAccount());
        }
        return userSnap.map(userPO -> {
            if (args.passwordMatch(userPO.getEncryptedPassword())) {
                return jwtFacade.generate(userPO.getId(), ActorRoleEnum.TENANT_USER.name(), null, null);
            }
            return null;
        }).orElseThrow(() -> new BusinessException("ACCOUNT_OR_PASSWORD_ERROR"));
    }
}

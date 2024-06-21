package com.trionesdev.mes.domain.core.domains.user.service.impl;

import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.commons.exception.DuplicatedException;
import com.trionesdev.mes.domain.core.domains.user.entity.User;
import com.trionesdev.mes.domain.core.domains.user.internal.UserBeanConvert;
import com.trionesdev.mes.domain.core.domains.user.manager.impl.UserManager;
import com.trionesdev.mes.domain.core.domains.user.repository.po.UserPO;
import com.trionesdev.mes.domain.core.domains.user.service.UserService;
import com.trionesdev.mes.domain.core.dto.user.AccountSignInArg;
import com.trionesdev.mes.domain.core.dto.user.AccountSignInArg.AccountType;
import com.trionesdev.mes.domain.core.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
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
    public Optional<UserDTO> findUserById(String id) {
        return userManager.findUserById(id).map(convert::userPoToDTO);
    }

    @Override
    public Optional<UserDTO> findUserByUsername(String username) {
        return userManager.findUserByUsername(username).map(convert::userPoToDTO);
    }

    @Override
    public Optional<UserDTO> findUserByPhone(String phone) {
        return userManager.findUserByPhone(phone).map(convert::userPoToDTO);
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
    public UserDTO accountSignIn(AccountSignInArg args) {
        Optional<UserPO> userSnap = Optional.empty();
        if (Objects.equals(AccountSignInArg.AccountType.PHONE, args.getAccountType())) {
            userSnap = userManager.findUserByPhone(args.getAccount());
        } else if (Objects.equals(AccountType.USERNAME, args.getAccountType())) {
            userSnap = userManager.findUserByUsername(args.getAccount());
        }
        return userSnap.map(t -> {
            if (args.passwordMatch(t.getEncodedPassword())) {
                return convert.userPoToDTO(t);
            } else {
                return null;
            }
        }).orElseThrow(() -> new BusinessException("ACCOUNT_OR_PASSWORD_ERROR"));
    }

}

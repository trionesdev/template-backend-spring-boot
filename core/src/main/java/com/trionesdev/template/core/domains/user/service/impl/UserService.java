package com.trionesdev.template.core.domains.user.service.impl;

import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.context.actor.ActorRoleEnum;
import com.trionesdev.commons.core.jwt.JwtClaims;
import com.trionesdev.commons.core.jwt.JwtFacade;
import com.trionesdev.commons.exception.BusinessException;
import com.trionesdev.commons.exception.NotFoundException;
import com.trionesdev.commons.model.ActorProfile;
import com.trionesdev.template.core.domains.notification.provider.impl.NotificationProvider;
import com.trionesdev.template.core.domains.org.provider.OrgProvider;
import com.trionesdev.template.core.domains.user.dto.*;
import com.trionesdev.template.core.domains.user.internal.UserDomainConvert;
import com.trionesdev.template.core.domains.user.internal.entity.User;
import com.trionesdev.template.core.domains.user.manager.impl.UserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static com.trionesdev.template.core.domains.user.internal.UserErrors.*;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserDomainConvert convert;
    private final ActorContext actorContext;
    private final UserManager userManager;
    private final JwtFacade jwtFacade;
    private final OrgProvider orgProvider;
    private final NotificationProvider notificationProvider;

    /**
     * 手机号码注册
     *
     * @param createCmd
     */
    public void phoneSignUp(UserCreateCmd createCmd) {
        var verifySuccess = notificationProvider.verifySmsValidationCode(createCmd.getPhone(), createCmd.getVerificationCode());
        if (!verifySuccess) {
            throw new BusinessException(VALIDATION_CODE_ERROR);
        }
        userManager.createUser(convert.from(createCmd));
    }

    private JwtClaims generateJwtClaims(User user) {
        var tenantMember = orgProvider.getCurrentTenantMember(user.getLatestTenantId(), user.getId());
        if (Objects.nonNull(tenantMember)) {
            return JwtClaims.builder().role(ActorRoleEnum.USER.name()).tenantId(tenantMember.getTenantId()).tenantMemberId(tenantMember.getId()).build();
        }
        return JwtClaims.builder().role(ActorRoleEnum.USER.name()).build();
    }

    public String accountSignIn(AccountSignInCmd cmd) {
        var user = User.builder().account(cmd.getAccount()).password(cmd.getPassword()).build();
        return userManager.findUserByAccount(user).map(userRes -> {
            return jwtFacade.generate(userRes.getId(), generateJwtClaims(userRes));
        }).orElseThrow(() -> new NotFoundException(ACCOUNT_OR_PWD_ERROR));
    }

    public String smsSignIn(SmsSignInCmd cmd) {
        boolean verifySuccess = notificationProvider.verifySmsValidationCode(cmd.getPhone(), cmd.getVerificationCode());
        if (!verifySuccess) {
            throw new BusinessException(VALIDATION_CODE_ERROR);
        }
        return userManager.findUserByPhone(cmd.getPhone()).map(userRes -> {
            return jwtFacade.generate(userRes.getId(), generateJwtClaims(userRes));
        }).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
    }

    public Optional<UserDTO> findUserById(String id) {
        return userManager.findUserById(id).map(user -> {
            return convert.userEntityToDTO(user);
        });
    }

    public Optional<ActorProfile> findActorProfile() {
        return userManager.findUserById(actorContext.getUserId()).map(user -> {
            return ActorProfile.builder()
                    .role(ActorRoleEnum.USER.name())
                    .nickname(user.getNickname())
                    .userId(user.getId())
                    .avatar(user.getAvatar())
                    .tenantId(actorContext.getTenantId())
                    .build();
        });
    }

    public void changeActorPassword(ActorChangePasswordCmd cmd) {
        userManager.findUserById(actorContext.getUserId()).ifPresent(userSnap -> {
            userSnap.setPassword(cmd.getPassword());
            userManager.updateUserById(userSnap);
        });
    }

    public void updateUserById(User user) {
        userManager.updateUserById(user);
    }

    public void actorChangeTenant(String tenantId) {
        userManager.findUserById(actorContext.getUserId()).ifPresent(user -> {
            userManager.changeTenant(user, tenantId);
        });
    }


}

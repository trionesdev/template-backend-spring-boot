package com.trionesdev.template.core.domains.boss.service.impl;

import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.context.actor.ActorRoleEnum;
import com.trionesdev.commons.core.jwt.JwtClaims;
import com.trionesdev.commons.core.jwt.JwtFacade;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.core.util.PageUtils;
import com.trionesdev.commons.exception.NotFoundException;
import com.trionesdev.commons.model.ActorProfile;
import com.trionesdev.template.core.domains.boss.dao.criteria.BossUserCriteria;
import com.trionesdev.template.core.domains.boss.dto.user.BossUserDTO;
import com.trionesdev.template.core.domains.boss.dto.user.cmd.BossAccountSignInCmd;
import com.trionesdev.template.core.domains.boss.dto.user.cmd.BossUserCreateCmd;
import com.trionesdev.template.core.domains.boss.dto.user.cmd.BossUserUpdateCmd;
import com.trionesdev.template.core.domains.boss.internal.BossUserDomainConvert;
import com.trionesdev.template.core.domains.boss.manager.impl.BossUserManager;
import com.trionesdev.template.core.domains.boss.repository.aggregate.entity.BossUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.trionesdev.template.core.domains.user.internal.UserErrors.ACCOUNT_OR_PWD_ERROR;

@RequiredArgsConstructor
@Service
public class BossUserService {
    private final BossUserDomainConvert convert;
    private final JwtFacade jwtFacade;
    private final ActorContext actorContext;
    private final BossUserManager bossUserManager;

    private JwtClaims generateJwtClaims(BossUser user) {
        return JwtClaims.builder().role(ActorRoleEnum.BOSS_USER.name()).build();
    }

    public void createBossUser(BossUserCreateCmd cmd) {
        var bossUser = convert.userCreateCmdToEntity(cmd);
        bossUserManager.create(bossUser);
    }

    public void updateBossUserById(BossUserUpdateCmd cmd) {
        var bossUser = convert.userUpdateCmdToEntity(cmd);
        bossUserManager.updateById(bossUser);
    }

    public Optional<BossUserDTO> findBossUserById(String id) {
        return bossUserManager.findUserById(id).map(convert::userEntityToDto);
    }

    public Optional<ActorProfile> findActorProfile() {
        return bossUserManager.findUserById(actorContext.getUserId()).map(user -> {
            return ActorProfile.builder()
                    .role(ActorRoleEnum.BOSS_USER.name())
                    .nickname(user.getNickname())
                    .userId(user.getId())
                    .avatar(user.getAvatar())
                    .build();
        });
    }

    public String accountSignIn(BossAccountSignInCmd cmd) {
        var user = BossUser.builder().account(cmd.getAccount()).password(cmd.getPassword()).build();
        return bossUserManager.findUserByAccount(user).map(userRes -> {
            return jwtFacade.generate(userRes.getId(), generateJwtClaims(userRes));
        }).orElseThrow(() -> new NotFoundException(ACCOUNT_OR_PWD_ERROR));
    }

    private List<BossUserDTO> assembleBossUserDTOList(List<BossUser> list) {
        return list.stream().map(convert::userEntityToDto).collect(Collectors.toList());
    }

    public List<BossUserDTO> findUserList(BossUserCriteria criteria) {
        var users = bossUserManager.findUserList(criteria);
        return assembleBossUserDTOList(users);
    }

    public PageInfo<BossUserDTO> findUserPage(BossUserCriteria criteria) {
        var pageInfo = bossUserManager.findUserPage(criteria);
        return PageUtils.of(pageInfo, assembleBossUserDTOList(pageInfo.getRows()));
    }

}

package com.trionesdev.template.core.domains.bossuser.service.impl;

import com.trionesdev.template.core.domains.bossuser.dto.BossUserDTO;
import com.trionesdev.template.core.domains.bossuser.dto.cmd.BossUserCreateCmd;
import com.trionesdev.template.core.domains.bossuser.dto.cmd.BossUserUpdateCmd;
import com.trionesdev.template.core.domains.bossuser.internal.BossUserDomainConvert;
import com.trionesdev.template.core.domains.bossuser.manager.impl.BossUserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BossUserService {
    private final BossUserDomainConvert convert;
    private final BossUserManager bossUserManager;

    public void createBossUser(BossUserCreateCmd cmd) {
        var bossUser = convert.userCreateCmdToEntity(cmd);
        bossUserManager.create(bossUser);
    }

    public void updateBossUserById(BossUserUpdateCmd cmd) {
        var bossUser = convert.userUpdateCmdToEntity(cmd);
        bossUserManager.updateById(bossUser);
    }

    public Optional<BossUserDTO> findBossUserById(String id) {
        return bossUserManager.findById(id).map(convert::userEntityToDto);
    }
}

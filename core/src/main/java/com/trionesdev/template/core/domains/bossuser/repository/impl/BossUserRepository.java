package com.trionesdev.template.core.domains.bossuser.repository.impl;

import com.trionesdev.template.core.domains.bossuser.dao.impl.BossUserDAO;
import com.trionesdev.template.core.domains.bossuser.internal.BossUserDomainConvert;
import com.trionesdev.template.core.domains.bossuser.repository.aggregate.entity.BossUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BossUserRepository {
    private final BossUserDomainConvert convert;
    private final BossUserDAO bossUserDAO;

    public void save(BossUser bossUser) {
        var bossUserPo = convert.userEntityToPo(bossUser);
        bossUserDAO.save(bossUserPo);
    }

    public void updateById(BossUser bossUser) {
        var bossUserPo = convert.userEntityToPo(bossUser);
        bossUserDAO.updateById(bossUserPo);
    }

    public Optional<BossUser> findById(String id) {
        return Optional.ofNullable(bossUserDAO.getById(id)).map(convert::usePoToEntity);
    }
}

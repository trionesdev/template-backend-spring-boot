package com.trionesdev.template.core.domains.bossuser.manager.impl;

import com.trionesdev.template.core.domains.bossuser.repository.aggregate.entity.BossUser;
import com.trionesdev.template.core.domains.bossuser.repository.impl.BossUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BossUserManager {
    private final BossUserRepository bossUserRepository;

    public void create(BossUser bossUser) {
        bossUserRepository.save(bossUser);
    }

    public void updateById(BossUser bossUser) {
        bossUserRepository.save(bossUser);
    }

    public Optional<BossUser> findById(String id) {
        return bossUserRepository.findById(id);
    }
}

package com.trionesdev.template.core.domains.bossuser.manager.impl;

import com.trionesdev.template.core.domains.bossuser.dao.impl.BossUserDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BossUserManager {
    private final BossUserDAO bossUserDAO;
}

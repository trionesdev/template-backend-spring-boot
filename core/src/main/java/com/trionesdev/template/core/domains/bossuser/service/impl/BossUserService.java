package com.trionesdev.template.core.domains.bossuser.service.impl;

import com.trionesdev.template.core.domains.bossuser.manager.impl.BossUserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BossUserService {
    private final BossUserManager bossUserManager;
}

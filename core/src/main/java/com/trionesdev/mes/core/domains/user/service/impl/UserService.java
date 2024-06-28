package com.trionesdev.mes.core.domains.user.service.impl;

import com.trionesdev.mes.core.domains.user.dto.UserCreateCmd;
import com.trionesdev.mes.core.domains.user.internal.UserBeanConvert;
import com.trionesdev.mes.core.domains.user.manager.impl.UserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserBeanConvert convert;
    private final UserManager userManager;

    public void createUser(UserCreateCmd createCmd) {
        userManager.createUser(convert.from(createCmd));
    }



}

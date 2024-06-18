package com.trionesdev.mes.domain.core.provider.ssp.user;

import com.trionesdev.mes.domain.core.dto.user.UserBindDTO;
import com.trionesdev.mes.domain.core.dto.user.UserCreateDTO;

public interface UserProvider {
    String createUser(UserCreateDTO user);

    String bindUser(UserBindDTO user);
}

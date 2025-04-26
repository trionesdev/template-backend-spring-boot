package com.trionesdev.template.rest.backend.domains.user.internal;

import com.trionesdev.template.core.domains.user.dto.cmd.AccountSignInCmd;
import com.trionesdev.template.core.domains.user.dto.cmd.SmsSignInCmd;
import com.trionesdev.template.core.domains.user.dto.cmd.UserCreateCmd;
import com.trionesdev.template.core.domains.user.internal.entity.User;
import com.trionesdev.template.rest.backend.domains.user.controller.ro.AccountSignInRO;
import com.trionesdev.template.rest.backend.domains.user.controller.ro.ActorUserUpdateRO;
import com.trionesdev.template.rest.backend.domains.user.controller.ro.SmsSignInRO;
import com.trionesdev.template.rest.backend.domains.user.controller.ro.SmsSignUpRO;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface UserBeRestConvert {
    AccountSignInCmd from(AccountSignInRO record);

    SmsSignInCmd from(SmsSignInRO record);

    UserCreateCmd from(SmsSignUpRO args);

    User from(ActorUserUpdateRO args);
}

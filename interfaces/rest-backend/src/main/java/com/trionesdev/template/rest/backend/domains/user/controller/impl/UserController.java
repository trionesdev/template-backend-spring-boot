package com.trionesdev.template.rest.backend.domains.user.controller.impl;

import com.trionesdev.commons.context.actor.ActorContext;
import com.trionesdev.commons.model.ActorProfile;
import com.trionesdev.template.core.domains.user.dto.cmd.ActorChangePasswordCmd;
import com.trionesdev.template.core.domains.user.dto.UserDTO;
import com.trionesdev.template.core.domains.user.service.impl.UserService;
import com.trionesdev.template.rest.backend.domains.user.controller.ro.ChangeTenantRO;
import com.trionesdev.template.rest.backend.domains.user.controller.ro.UpdateActorPasswordRO;
import com.trionesdev.template.rest.backend.domains.user.controller.ro.ActorUserUpdateRO;
import com.trionesdev.template.rest.backend.domains.user.internal.UserBeRestConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.trionesdev.template.rest.backend.domains.user.internal.UserConstants.USER_PATH;

@Tag(name = "用户/用户")
@RequiredArgsConstructor
@RestController
@RequestMapping(USER_PATH)
public class UserController {
    private final UserBeRestConvert convert;
    private final ActorContext actorContext;
    private final UserService userService;

    /**
     * if the application is for backend management we suggest the api in tenant module.
     * @see com.trionesdev.template.rest.tenant.domains.org.controller.impl.TenantController#fineActorProfile()
     * @return
     */
    @Operation(summary = "获取当前用户信息(ActorProfile)")
    @GetMapping(value = "actor/profile")
    public ActorProfile findActorUser() {
        return userService.findActorProfile().orElse(null);
    }

    @Operation(summary = "切换租户")
    @PutMapping(value = "actor/change-tenant")
    public void changeTenant(@Validated @RequestBody ChangeTenantRO args) {
        userService.actorChangeTenant(args.getTenantId());
    }

    @Operation(summary = "获取当前用户的用户信息")
    @GetMapping(value = "actor/user")
    public UserDTO findUser() {
        return userService.findUserById(actorContext.getUserId()).orElse(null);
    }

    @Operation(summary = "修改当前用户密码")
    @PutMapping(value = "actor/password")
    public void updateActorPassword(@Validated @RequestBody UpdateActorPasswordRO args) {
        var cmd = ActorChangePasswordCmd.builder().password(args.getPassword()).build();
        userService.changeActorPassword(cmd);
    }

    @Operation(summary = "修改当前用户信息")
    @PutMapping(value = "actor/user")
    public void updateActorUser(@Validated @RequestBody ActorUserUpdateRO args) {
        var user = convert.from(args);
        user.setId(actorContext.getUserId());
        userService.updateUserById(user);
    }
}

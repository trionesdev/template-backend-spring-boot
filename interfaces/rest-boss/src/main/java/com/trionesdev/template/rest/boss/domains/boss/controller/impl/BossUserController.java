package com.trionesdev.template.rest.boss.domains.boss.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.model.ActorProfile;
import com.trionesdev.template.core.domains.boss.dto.user.BossUserDTO;
import com.trionesdev.template.core.domains.boss.dto.user.cmd.BossActorChangePasswordCmd;
import com.trionesdev.template.core.domains.boss.service.impl.BossUserService;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.user.*;
import com.trionesdev.template.rest.boss.domains.boss.internal.BossUserRestBossConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import static com.trionesdev.template.rest.boss.domains.boss.internal.BossConstants.BOSS_USER_PATH;

@Tag(name = "BOSS/用户")
@RequiredArgsConstructor
@RestController("boss_userController")
@RequestMapping(BOSS_USER_PATH)
public class BossUserController {
    private final BossUserRestBossConvert convert;
    private final BossUserService bossUserService;

    @Operation(summary = "新建用户")
    @PostMapping(value = "users")
    public void createUser(@Validated @RequestBody BossUserCreateRO args) {
        var user = convert.userCreateCmdFromCreateRo(args);
        bossUserService.createBossUser(user);
    }

    @Operation(summary = "根据ID删除用户")
    @DeleteMapping(value = "users/{id}")
    public void deleteUserById(@PathVariable(value = "id") String id) {
        bossUserService.deleteBossUserById(id);
    }

    @Operation(summary = "根据ID修改用户")
    @PutMapping(value = "users/{id}")
    public void updateUserById(@PathVariable(value = "id") String id, @Validated @RequestBody BossUserUpdateRO args) {
        var user = convert.userUpdateCmdFromUpdateRo(args);
        user.setId(id);
        bossUserService.updateBossUserById(user);
    }

    @Operation(summary = "获取当前用户信息(ActorProfile)")
    @GetMapping(value = "actor/profile")
    public ActorProfile findActorUser() {
        return bossUserService.findActorProfile().orElse(null);
    }

    @Operation(summary = "根据ID获取用户信息")
    @GetMapping(value = "users/{id}")
    public BossUserDTO findBossUserById(@PathVariable(value = "id") String id) {
        return bossUserService.findBossUserById(id).orElse(null);
    }

    @Operation(summary = "获取用户信息(分页)")
    @GetMapping(value = "users/page")
    public PageInfo<BossUserDTO> findUserPage(
            @RequestParam(value = "pageNum") Integer pageNnm,
            @RequestParam(value = "pageSize") Integer pageSize,
            BossUserQueryRO query
    ) {
        var criteria = convert.bossUserCriteriaFromQueryRo(query);
        criteria.setPageNum(pageNnm);
        criteria.setPageSize(pageSize);
        return bossUserService.findUserPage(criteria);
    }

    @Operation(summary = "需修改当前用户信息")
    @PutMapping(value = "user/actor")
    public void updateActorProfile(@Validated @RequestBody BossActorProfileUpdateRO args) {
        var user = convert.userUpdateCmdFromActorUpdateRo(args);
        bossUserService.updateActorProfile(user);
    }

    @Operation(summary = "修改当前用户密码")
    @PutMapping(value = "user/actor/password")
    public void actorChangePwd(@Validated @RequestBody BossActorChangePasswordRO args) {
        var changePwd = BossActorChangePasswordCmd.builder().oldPassword(args.getOldPassword()).password(args.getPassword()).build();
        bossUserService.changeActorPassword(changePwd);
    }

}

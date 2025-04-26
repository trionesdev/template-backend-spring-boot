package com.trionesdev.template.rest.boss.domains.boss.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.commons.model.ActorProfile;
import com.trionesdev.template.core.domains.boss.dto.BossUserDTO;
import com.trionesdev.template.core.domains.boss.service.impl.BossUserService;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.BossUserQueryRO;
import com.trionesdev.template.rest.boss.domains.boss.internal.BossRestBossConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import static com.trionesdev.template.rest.boss.domains.boss.internal.BossConstants.USER_PATH;

@Tag(name = "用户")
@RequiredArgsConstructor
@RestController("boss_userController")
@RequestMapping(USER_PATH)
public class BossUserController {
    private final BossRestBossConvert convert;
    private final BossUserService bossUserService;

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

}

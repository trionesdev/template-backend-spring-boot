package com.trionesdev.template.rest.boss.domains.user.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.template.core.domains.user.dto.UserDTO;
import com.trionesdev.template.core.domains.user.service.impl.UserService;
import com.trionesdev.template.rest.boss.domains.user.controller.ro.UserQueryRO;
import com.trionesdev.template.rest.boss.domains.user.internal.UserRestBossConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.trionesdev.template.rest.boss.domains.user.internal.UserConstants.USER_PATH;

@Tag(name = "用户")
@RequiredArgsConstructor
@RestController("boss_userController")
@RequestMapping(USER_PATH)
public class UserController {
    private final UserRestBossConvert convert;
    private final UserService userService;

    @Operation(summary = "用户分页")
    @GetMapping(value = "user/page")
    public PageInfo<UserDTO> queryUserPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            UserQueryRO query) {
        var userCriteria = convert.userCriteriaFromQuery(query);
        userCriteria.setPageNum(pageNum);
        userCriteria.setPageSize(pageSize);
        return userService.findUserPage(userCriteria);
    }
}

package com.trionesdev.mes.rest.backend.domains.user.controller.impl;

import com.trionesdev.mes.rest.backend.domains.user.internal.UserRestConstants;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "用户")
@RequiredArgsConstructor
@RestController
@RequestMapping(UserRestConstants.USER_PATH)
public class UserController {
}

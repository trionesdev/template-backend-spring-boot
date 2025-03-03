package com.trionesdev.template.rest.boss.domains.org.controller.impl;

import com.trionesdev.template.core.domains.org.service.impl.TenantService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.trionesdev.template.rest.boss.domains.perm.internal.PermConstants.PERM_PATH;

@Tag(name = "组织-租户")
@RequiredArgsConstructor
@RestController("boss_tenantController")
@RequestMapping(PERM_PATH)
public class TenantController {

    private final TenantService tenantService;

}

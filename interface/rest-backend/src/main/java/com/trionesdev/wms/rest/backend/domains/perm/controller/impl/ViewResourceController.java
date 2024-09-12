package com.trionesdev.wms.rest.backend.domains.perm.controller.impl;

import com.trionesdev.wms.core.domains.perm.dao.po.ViewResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.service.impl.ViewResourceService;
import com.trionesdev.wms.rest.backend.domains.perm.internal.PermBeRestConvert;
import com.trionesdev.wms.rest.backend.domains.perm.internal.PermRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "权限资源-视图资源")
@RequiredArgsConstructor
@RestController
@RequestMapping(PermRestConstants.PERM_PATH)
public class ViewResourceController {
    private final PermBeRestConvert convert;
    private final ViewResourceService viewResourceService;

    @Operation(summary = "创建资源草稿")
    @PostMapping(value = "view/resource/drafts")
    public void createDraft(@Validated @RequestBody ViewResourceDraftPO args) {
        var r = convert.from(args);
    }
}

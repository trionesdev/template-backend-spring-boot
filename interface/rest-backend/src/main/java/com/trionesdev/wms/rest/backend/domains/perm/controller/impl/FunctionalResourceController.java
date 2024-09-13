package com.trionesdev.wms.rest.backend.domains.perm.controller.impl;

import com.trionesdev.wms.core.domains.perm.service.impl.FunctionalResourceService;
import com.trionesdev.wms.rest.backend.domains.perm.controller.ro.FunctionalResourceDraftCreateRO;
import com.trionesdev.wms.rest.backend.domains.perm.controller.ro.FunctionalResourceDraftUpdateRO;
import com.trionesdev.wms.rest.backend.domains.perm.internal.PermBeRestConvert;
import com.trionesdev.wms.rest.backend.domains.perm.internal.PermRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "权限资源-视图资源")
@RequiredArgsConstructor
@RestController
@RequestMapping(PermRestConstants.PERM_PATH)
public class FunctionalResourceController {
    private final PermBeRestConvert convert;
    private final FunctionalResourceService functionalResourceService;

    @Operation(summary = "创建资源草稿")
    @PostMapping(value = "functional/resource/drafts")
    public void createDraft(@Validated @RequestBody FunctionalResourceDraftCreateRO args) {
        var draft = convert.from(args);
        functionalResourceService.createResourceDraft(draft);
    }

    @Operation(summary = "根据ID更新功能资源草稿")
    @PutMapping(value = "functional/resource/drafts/{id}")
    public void updateDraft(@PathVariable(value = "id") String id, @Validated @RequestBody FunctionalResourceDraftUpdateRO args){
        var draft = convert.from(args);
        draft.setId(id);
        functionalResourceService.updateDraftById(draft);
    }

}

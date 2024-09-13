package com.trionesdev.wms.rest.boss.domains.perm.controller.impl;

import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.service.impl.FunctionalResourceService;
import com.trionesdev.wms.rest.boss.domains.perm.controller.ro.ResourceDraftCreateRO;
import com.trionesdev.wms.rest.boss.domains.perm.controller.ro.ResourceDraftReleaseRO;
import com.trionesdev.wms.rest.boss.domains.perm.controller.ro.ResourceDraftUpdateRO;
import com.trionesdev.wms.rest.boss.domains.perm.internal.PermBeRestConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.trionesdev.wms.rest.boss.domains.perm.internal.PermConstants.PERM_PATH;

@Tag(name = "权限管理-资源管理", description = "权限管理-资源管理")
@RequiredArgsConstructor
@RestController
@RequestMapping(PERM_PATH)
public class ResourceController {
    private final PermBeRestConvert convert;
    private final FunctionalResourceService viewResourceService;

    @Operation(summary = "创建资源草稿")
    @PostMapping(value = "resource/drafts")
    public void createDraft(@Validated @RequestBody ResourceDraftCreateRO args) {
        var draft = convert.from(args);
        viewResourceService.createResourceDraft(draft);
    }

    @Operation(summary = "根据ID资源草稿")
    @DeleteMapping(value = "resource/drafts/{id}")
    public void deleteDraftById(@PathVariable(value = "id") String id) {
        viewResourceService.deleteResourceDraftById(id);
    }

    @Operation(summary = "根据ID更新资源草稿")
    @PutMapping(value = "resource/drafts/{id}")
    public void updateDraft(@PathVariable(value = "id") String id, @Validated @RequestBody ResourceDraftUpdateRO args) {
        var draft = convert.from(args);
        draft.setId(id);
        viewResourceService.updateDraftById(draft);
    }

    @Operation(summary = "获取资源草稿列表")
    @GetMapping(value = "resource/draft/list")
    public List<FunctionalResourceDraftPO> findDraftsByClientType(@RequestParam ClientType clientType) {
        return viewResourceService.findDraftsByClientType(clientType);
    }

    @Operation(summary = "发布资源草稿")
    @PutMapping(value = "resource/draft/release")
    public void releaseDraft(@Validated @RequestBody ResourceDraftReleaseRO args) {
        viewResourceService.releaseDraft(args.getClientType());
    }
}

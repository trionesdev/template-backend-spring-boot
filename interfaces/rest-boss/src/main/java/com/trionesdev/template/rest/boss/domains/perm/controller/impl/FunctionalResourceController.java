package com.trionesdev.template.rest.boss.domains.perm.controller.impl;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.template.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.template.core.domains.perm.service.impl.FunctionalResourceService;
import com.trionesdev.template.core.domains.perm.shared.enums.ClientType;
import com.trionesdev.template.rest.boss.domains.perm.controller.ro.FunctionalResourceDraftCreateRO;
import com.trionesdev.template.rest.boss.domains.perm.controller.ro.FunctionalResourceDraftUpdateRO;
import com.trionesdev.template.rest.boss.domains.perm.internal.PermBossRestConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.trionesdev.template.rest.boss.domains.perm.internal.PermConstants.PERM_PATH;

@Tag(name = "权限-功能资源")
@RequiredArgsConstructor
@RestController("boss_functionalResourceController")
@RequestMapping(PERM_PATH)
public class FunctionalResourceController {
    private final PermBossRestConvert convert;
    private final FunctionalResourceService functionalResourceService;


    @Operation(summary = "创建功能资源草稿")
    @PostMapping(value = "functional-resource-drafts")
    public void createResourceDraft(@Validated @RequestBody FunctionalResourceDraftCreateRO args) {
        var resource = convert.from(args);
        functionalResourceService.createResourceDraft(resource);
    }

    @Operation(summary = "根据ID删除功能资源草稿")
    @DeleteMapping(value = "functional-resource-drafts/{id}")
    public void deleteResourceDraftById(@PathVariable String id) {
        functionalResourceService.deleteResourceDraftById(id);
    }

    @Operation(summary = "根据ID更新功能资源")
    @PutMapping(value = "functional-resource-draft/{id}")
    public void updateResourceDraft(@PathVariable String id, @Validated @RequestBody FunctionalResourceDraftUpdateRO args) {
        var resource = convert.from(args);
        resource.setId(id);
        functionalResourceService.updateResourceDraftById(resource);
    }

    @Operation(summary = "根据ID获取功能资源草稿")
    @GetMapping(value = "functional-resource-drafts/{id}")
    public FunctionalResourceDraftPO findResourceDraftById(@PathVariable String id) {
        return functionalResourceService.findResourceDraftById(id).orElse(null);
    }

    @Operation(summary = "获取功能资源草稿列表（树形）")
    @GetMapping(value = "functional-resource-draft/tree")
    public List<Tree<String>> findResourceDraftTree(@RequestParam(value = "appCode", required = false) String appCode, @RequestParam(value = "clientType", required = false) ClientType clientType) {
        return functionalResourceService.findResourceDraftTreeByClientType(appCode, clientType);
    }

    @Operation(summary = "发布草稿")
    @PutMapping(value = "functional-resource-draft/release")
    public void releaseResourceDraft(@RequestParam(value = "appCode", required = false) String appCode, @RequestParam(value = "clientType", required = false) ClientType clientType) {
        functionalResourceService.releaseResourceDraft(appCode, clientType);
    }

    @Operation(summary = "同步发布数据到草稿")
    @PutMapping(value = "functional-resource-draft/sync-from-release")
    public void syncResourceDraftFromRelease(@RequestParam(value = "appCode", required = false) String appCode, @RequestParam(value = "clientType", required = false) ClientType clientType) {
        functionalResourceService.syncResourceDraftFromRelease(appCode, clientType);
    }


    @Operation(summary = "获取功能资源列表（树形）")
    @GetMapping(value = "functional-resource/tree")
    public List<Tree<String>> findResourceTree(@RequestParam(value = "appCode", required = false) String appCode, @RequestParam(value = "clientType", required = false) ClientType clientType) {
        return functionalResourceService.findResourceTreeByClientType(appCode, clientType);
    }


}

package com.trionesdev.template.rest.boss.domains.boss.controller.impl;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.template.core.domains.boss.dao.po.BossFunctionalResourceDraftPO;
import com.trionesdev.template.core.domains.boss.service.impl.BossFunctionalResourceService;
import com.trionesdev.template.core.domains.boss.shared.enums.ClientType;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.perm.BossFunctionalResourceDraftCreateRO;
import com.trionesdev.template.rest.boss.domains.boss.controller.ro.perm.BossFunctionalResourceDraftUpdateRO;
import com.trionesdev.template.rest.boss.domains.boss.internal.BossPermRestBossConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.trionesdev.template.rest.boss.domains.boss.internal.BossConstants.BOSS_PERM_PATH;

@Tag(name = "权限-功能资源")
@RequiredArgsConstructor
@RestController
@RequestMapping(BOSS_PERM_PATH)
public class BossFunctionalResourceController {
    private final BossPermRestBossConvert convert;
    private final BossFunctionalResourceService functionalResourceService;

    @Operation(summary = "创建功能资源草稿")
    @PostMapping(value = "functional-resource-drafts")
    public void createResourceDraft(@Validated @RequestBody BossFunctionalResourceDraftCreateRO args) {
        var resource = convert.functionResPoFromCreateRo(args);
        functionalResourceService.createResourceDraft(resource);
    }

    @Operation(summary = "根据ID删除功能资源草稿")
    @DeleteMapping(value = "functional-resource-drafts/{id}")
    public void deleteResourceDraftById(@PathVariable String id) {
        functionalResourceService.deleteResourceDraftById(id);
    }

    @Operation(summary = "根据ID更新功能资源")
    @PutMapping(value = "functional-resource-draft/{id}")
    public void updateResourceDraft(@PathVariable String id, @Validated @RequestBody BossFunctionalResourceDraftUpdateRO args) {
        var resource = convert.functionResPoFromUpdateRo(args);
        resource.setId(id);
        functionalResourceService.updateResourceDraftById(resource);
    }

    @Operation(summary = "根据ID获取功能资源草稿")
    @GetMapping(value = "functional-resource-drafts/{id}")
    public BossFunctionalResourceDraftPO findResourceDraftById(@PathVariable String id) {
        return functionalResourceService.findResourceDraftById(id).orElse(null);
    }

    @Operation(summary = "获取功能资源草稿列表（树形）")
    @GetMapping(value = "functional-resource-draft/tree")
    public List<Tree<String>> findResourceDraftTree(@RequestParam(value = "clientType", required = false) ClientType clientType) {
        return functionalResourceService.findResourceDraftTreeByClientType(clientType);
    }

    @Operation(summary = "发布草稿")
    @PutMapping(value = "functional-resource-draft/release")
    public void releaseResourceDraft(@RequestParam(value = "clientType", required = false) ClientType clientType) {
        functionalResourceService.releaseResourceDraft(clientType);
    }

    @Operation(summary = "同步发布数据到草稿")
    @PutMapping(value = "functional-resource-draft/sync-from-release")
    public void syncResourceDraftFromRelease(@RequestParam(value = "clientType", required = false) ClientType clientType) {
        functionalResourceService.syncResourceDraftFromRelease(clientType);
    }


    @Operation(summary = "获取功能资源列表（树形）")
    @GetMapping(value = "functional-resource/tree")
    public List<Tree<String>> findResourceTree(@RequestParam(value = "clientType", required = false) ClientType clientType) {
        return functionalResourceService.findResourceTreeByClientType(clientType);
    }
    

}

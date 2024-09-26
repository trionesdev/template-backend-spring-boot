package com.trionesdev.wms.rest.boss.domains.perm.controller.impl;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.wms.core.domains.perm.dao.po.FunctionalResourceDraftPO;
import com.trionesdev.wms.core.domains.perm.dto.FunctionalResourceDTO;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.service.impl.FunctionalResourceService;
import com.trionesdev.wms.rest.boss.domains.perm.controller.ro.FunctionalResourceCreateRO;
import com.trionesdev.wms.rest.boss.domains.perm.controller.ro.FunctionalResourceDraftCreateRO;
import com.trionesdev.wms.rest.boss.domains.perm.controller.ro.FunctionalResourceDraftReleaseRO;
import com.trionesdev.wms.rest.boss.domains.perm.controller.ro.FunctionalResourceDraftUpdateRO;
import com.trionesdev.wms.rest.boss.domains.perm.internal.PermBossRestConvert;
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

@Tag(name = "权限-功能资源")
@RequiredArgsConstructor
@RestController("boss_functionalResourceController")
@RequestMapping(PERM_PATH)
public class FunctionalResourceController {
    private final PermBossRestConvert convert;
    private final FunctionalResourceService functionalResourceService;


    @Operation(summary = "创建功能资源")
    @PostMapping(value = "functional-resources")
    public void createResource(@Validated @RequestBody FunctionalResourceCreateRO args) {
        var resource = convert.from(args);
        functionalResourceService.createResource(resource);
    }

    @Operation(summary = "根据ID删除功能资源")
    @DeleteMapping(value = "functional-resources/{id}")
    public void deleteResourceById(@PathVariable String id) {
        functionalResourceService.deleteResourceById(id);
    }

    @Operation(summary = "根据ID更新功能资源")
    @PutMapping(value = "functional-resources/{id}")
    public void updateResource(@PathVariable String id, @Validated @RequestBody FunctionalResourceCreateRO args) {
        var resource = convert.from(args);
        resource.setId(id);
        functionalResourceService.updateResourceById(resource);
    }

    @Operation(summary = "根据ID获取功能资源")
    @GetMapping(value = "functional-resources/{id}")
    public FunctionalResourceDTO findResourceById(@PathVariable String id) {
        return functionalResourceService.findResourceById(id).orElse(null);
    }

    @Operation(summary = "获取功能资源列表（树形）")
    @GetMapping(value = "functional-resource/tree")
    public List<Tree<String>> findResourcesTree(@RequestParam(value = "appCode", required = false) String appCode, @RequestParam ClientType clientType) {
        return functionalResourceService.findResourceTreeByClientType(appCode, clientType);
    }








    @Operation(summary = "创建资源草稿")
    @PostMapping(value = "functional-resource/drafts")
    public void createDraft(@Validated @RequestBody FunctionalResourceDraftCreateRO args) {
        var draft = convert.from(args);
        functionalResourceService.createResourceDraft(draft);
    }

    @Operation(summary = "根据ID资源草稿")
    @DeleteMapping(value = "functional-resource/drafts/{id}")
    public void deleteDraftById(@PathVariable(value = "id") String id) {
        functionalResourceService.deleteResourceDraftById(id);
    }

    @Operation(summary = "根据ID更新资源草稿")
    @PutMapping(value = "functional-resource/drafts/{id}")
    public void updateDraft(@PathVariable(value = "id") String id, @Validated @RequestBody FunctionalResourceDraftUpdateRO args) {
        var draft = convert.from(args);
        draft.setId(id);
        functionalResourceService.updateDraftById(draft);
    }

    @Operation(summary = "根据ID获取资源草稿")
    @GetMapping(value = "functional-resource/drafts/{id}")
    public FunctionalResourceDraftPO findDraftById(@PathVariable String id) {
        return functionalResourceService.findDraftById(id).orElse(null);
    }

    @Operation(summary = "获取资源草稿列表")
    @GetMapping(value = "functional-resource/draft/tree")
    public List<Tree<String>> findDraftsByClientType(@RequestParam(value = "appIdentifier", required = false) String appIdentifier, @RequestParam ClientType clientType) {
        return functionalResourceService.findDraftTreeByClientType(appIdentifier, clientType);
    }

    @Operation(summary = "发布资源草稿")
    @PutMapping(value = "functional-resource/draft/release")
    public void releaseDraft(@Validated @RequestBody FunctionalResourceDraftReleaseRO args) {
        functionalResourceService.releaseDraft(args.getAppIdentifier(), args.getClientType());
    }

    @Operation(summary = "获取功能资源(树形列表)")
    @GetMapping(value = "functional-resource/tree1")
    public List<Tree<String>> findResourceTreeByClientType(@RequestParam(value = "appIdentifier", required = false) String appIdentifier, @RequestParam ClientType clientType) {
        return functionalResourceService.findResourceTreeByClientType(appIdentifier, clientType);
    }
}

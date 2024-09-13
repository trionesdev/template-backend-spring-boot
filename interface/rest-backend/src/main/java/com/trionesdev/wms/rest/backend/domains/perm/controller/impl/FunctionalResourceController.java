package com.trionesdev.wms.rest.backend.domains.perm.controller.impl;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.wms.core.domains.perm.internal.enums.ClientType;
import com.trionesdev.wms.core.domains.perm.service.impl.FunctionalResourceService;
import com.trionesdev.wms.rest.backend.domains.perm.internal.PermBeRestConvert;
import com.trionesdev.wms.rest.backend.domains.perm.internal.PermRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "权限-功能资源")
@RequiredArgsConstructor
@RestController
@RequestMapping(PermRestConstants.PERM_PATH)
public class FunctionalResourceController {
    private final PermBeRestConvert convert;
    private final FunctionalResourceService functionalResourceService;
    @Operation(summary = "获取功能资源(树形列表)")
    @GetMapping(value = "functional/resource/tree")
    public List<Tree<String>> findResourceTreeByClientType(@RequestParam ClientType clientType) {
        return functionalResourceService.findResourceTreeByClientType(clientType);
    }

}

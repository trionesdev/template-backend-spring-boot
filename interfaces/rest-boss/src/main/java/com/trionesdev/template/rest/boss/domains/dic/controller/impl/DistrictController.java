package com.trionesdev.template.rest.boss.domains.dic.controller.impl;

import cn.hutool.core.lang.tree.Tree;
import com.trionesdev.template.core.domains.dic.dao.po.DistrictPO;
import com.trionesdev.template.core.domains.dic.service.impl.DistrictService;
import com.trionesdev.template.rest.boss.domains.dic.controller.ro.DistrictQueryRO;
import com.trionesdev.template.rest.boss.domains.dic.internal.DicBossRestConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.trionesdev.template.rest.boss.domains.dic.internal.DicConstants.DIC_PATH;

@Tag(name = "字典-地区")
@RequiredArgsConstructor
@RestController("boss_districtController")
@RequestMapping(DIC_PATH)
public class DistrictController {
    private final DicBossRestConvert convert;
    private final DistrictService districtService;

    @Operation(summary = "根据ID获取地区信息")
    @GetMapping(value = "districts/{id}")
    public DistrictPO queryDistrictById(@PathVariable(value = "id") String id) {
        return districtService.findDistrictById(id).orElse(null);
    }

    @Operation(summary = "查询地区列表")
    @GetMapping(value = "district/list")
    public List<DistrictPO> queryDistricts(DistrictQueryRO query) {
        var criteria = convert.from(query);
        return districtService.findDistricts(criteria);
    }

    @Operation(summary = "获取地区数据(树形)")
    @GetMapping(value = "district/tree")
    public List<Tree<String>> queryDistrictTree() {
        return districtService.queryDistrictTree();
    }

}

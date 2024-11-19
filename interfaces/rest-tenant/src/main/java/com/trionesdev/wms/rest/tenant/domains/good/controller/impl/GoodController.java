package com.trionesdev.wms.rest.tenant.domains.good.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.good.dto.GoodDTO;
import com.trionesdev.wms.core.domains.good.service.impl.GoodService;
import com.trionesdev.wms.rest.tenant.domains.good.controller.ro.GoodQueryRO;
import com.trionesdev.wms.rest.tenant.domains.good.controller.ro.GoodRO;
import com.trionesdev.wms.rest.tenant.domains.good.internal.GoodBeRestConvert;
import com.trionesdev.wms.rest.tenant.domains.good.internal.GoodRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@Tag(name = "货物管理")
@RequiredArgsConstructor
@RestController
@RequestMapping(GoodRestConstants.GOOD_PATH)
public class GoodController {
    private final GoodBeRestConvert convert;
    private final GoodService goodService;

    @Operation(summary = "创建货物")
    @PostMapping("goods")
    public void create(@Validated @RequestBody GoodRO args) {
        var po = convert.from(args);
        goodService.create(po);
    }

    @Operation(summary = "根据ID更新货物")
    @PutMapping("goods/{id}")
    public void updateById(
            @PathVariable(value = "id") String id,
            @Validated @RequestBody GoodRO args
    ) {
        var po = convert.from(args);
        po.setId(id);
        goodService.updateById(po);
    }

    @Operation(summary = "根据ID查询货物")
    @GetMapping(value = "goods/{id}")
    public GoodDTO queryById(@PathVariable String id) {
        return goodService.findById(id).orElse(null);
    }

    @Operation(summary = "查询货物列表分页")
    @GetMapping("goods/page")
    public PageInfo<GoodDTO> queryPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            GoodQueryRO query) {
        var criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return goodService.findPage(criteria);
    }

    @Operation(summary = "查询货物列表")
    @GetMapping("goods/list")
    public List<GoodDTO> queryList(GoodQueryRO query) {
        var criteria = convert.from(query);
        return goodService.findList(criteria);
    }

    @Operation(summary = "批量删除货物")
    @Parameter(name = "ids", description = "以,分割的id字符串，形如：1,2,3")
    @DeleteMapping(value = "goods/{ids}")
    public void deleteByIds(@PathVariable(value = "ids") List<String> ids) {
        goodService.deleteByIds(ids);
    }
}

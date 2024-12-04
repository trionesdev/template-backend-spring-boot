package com.trionesdev.wms.rest.tenant.domains.goods.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.goods.dto.GoodsDTO;
import com.trionesdev.wms.core.domains.goods.service.impl.GoodsService;
import com.trionesdev.wms.rest.tenant.domains.goods.controller.ro.GoodsQueryRO;
import com.trionesdev.wms.rest.tenant.domains.goods.controller.ro.GoodsRO;
import com.trionesdev.wms.rest.tenant.domains.goods.internal.GoodsBeRestConvert;
import com.trionesdev.wms.rest.tenant.domains.goods.internal.GoodsRestConstants;
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
@RequestMapping(GoodsRestConstants.GOOD_PATH)
public class GoodsController {
    private final GoodsBeRestConvert convert;
    private final GoodsService goodsService;

    @Operation(summary = "创建货物")
    @PostMapping("goods")
    public void create(@Validated @RequestBody GoodsRO args) {
        var po = convert.from(args);
        goodsService.create(po);
    }

    @Operation(summary = "根据ID更新货物")
    @PutMapping("goods/{id}")
    public void updateById(
            @PathVariable(value = "id") String id,
            @Validated @RequestBody GoodsRO args
    ) {
        var po = convert.from(args);
        po.setId(id);
        goodsService.updateById(po);
    }

    @Operation(summary = "根据ID查询货物")
    @GetMapping(value = "goods/{id}")
    public GoodsDTO queryById(@PathVariable String id) {
        return goodsService.findById(id).orElse(null);
    }

    @Operation(summary = "查询货物列表分页")
    @GetMapping("goods/page")
    public PageInfo<GoodsDTO> queryPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            GoodsQueryRO query) {
        var criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return goodsService.findPage(criteria);
    }

    @Operation(summary = "查询货物列表")
    @GetMapping("goods/list")
    public List<GoodsDTO> queryList(GoodsQueryRO query) {
        var criteria = convert.from(query);
        return goodsService.findList(criteria);
    }

    @Operation(summary = "批量删除货物")
    @Parameter(name = "ids", description = "以,分割的id字符串，形如：1,2,3")
    @DeleteMapping(value = "goods/{ids}")
    public void deleteByIds(@PathVariable(value = "ids") List<String> ids) {
        goodsService.deleteByIds(ids);
    }
}

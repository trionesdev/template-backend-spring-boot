package com.trionesdev.backend.rest.backend.domains.masterdata.controller.impl;

import com.trionesdev.backend.rest.backend.domains.masterdata.controller.query.ProductDefinitionQuery;
import com.trionesdev.backend.rest.backend.domains.masterdata.controller.ro.ProductDefinitionCreateRO;
import com.trionesdev.backend.rest.backend.domains.masterdata.controller.ro.ProductDefinitionUpdateRO;
import com.trionesdev.backend.rest.backend.domains.masterdata.internal.MasterDataBeRestBeanConvert;
import com.trionesdev.backend.rest.backend.domains.masterdata.internal.MasterDataRestConstants;
import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.ProductDefinition;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.ProductDefinitionService;
import com.trionesdev.mes.domain.core.dto.masterdata.ProductDefinitionDTO;
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

@Tag(name = "产品定义")
@RequiredArgsConstructor
@RestController
@RequestMapping(MasterDataRestConstants.MASTER_DATA_PATH)
public class ProductDefinitionController {
    private final MasterDataBeRestBeanConvert masterDataBeRestBeanConvert;
    private final ProductDefinitionService productDefinitionService;

    @Operation(summary = "创建产品定义")
    @PostMapping("product-definitions")
    public void createProductDefinition(@Validated @RequestBody ProductDefinitionCreateRO args) {
        productDefinitionService.create(masterDataBeRestBeanConvert.from(args));
    }

    @Operation(summary = "根据ID删除产品定义")
    @DeleteMapping("product-definitions/{id}")
    public void deleteById(@PathVariable("id") String id) {
        productDefinitionService.deleteById(id);
    }

    @Operation(summary = "根据ID更新产品定义")
    @PutMapping("product-definitions/{id}")
    public void updateById(@PathVariable("id") String id, @Validated @RequestBody ProductDefinitionUpdateRO args) {
        ProductDefinition productDefinition = masterDataBeRestBeanConvert.from(args);
        productDefinition.setId(id);
        productDefinitionService.updateById(productDefinition);
    }

    @Operation(summary = "根据ID查询产品定义")
    @GetMapping("product-definitions/{id}")
    public ProductDefinitionDTO findById(@PathVariable("id") String id) {
        return productDefinitionService.findById(id).orElse(null);
    }

    @Operation(summary = "分页查询产品定义")
    @GetMapping("product-definitions/page")
    public PageInfo<ProductDefinitionDTO> findPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            ProductDefinitionQuery query
    ) {
        ProductDefinitionCriteria criteria = masterDataBeRestBeanConvert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return productDefinitionService.findPage(criteria);
    }

}

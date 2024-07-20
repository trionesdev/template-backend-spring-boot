package com.trionesdev.mes.rest.backend.domains.masterdata.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.core.domains.masterdata.internal.aggreate.entity.ProductBom;
import com.trionesdev.mes.core.domains.masterdata.dao.criteria.ProductDefinitionCriteria;
import com.trionesdev.mes.core.domains.masterdata.service.impl.ProductBomService;
import com.trionesdev.mes.core.domains.masterdata.dto.ProductBomDTO;
import com.trionesdev.mes.core.domains.masterdata.dto.ProductMaterialDTO;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro.ProductBomCreateRO;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataBeRestBeanConvert;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "物料清单")
@RequiredArgsConstructor
@RestController
@RequestMapping(MasterDataRestConstants.MASTER_DATA_PATH)
public class ProductBomController {
    private final MasterDataBeRestBeanConvert convert;
    private final ProductBomService productBomService;

    @Operation(summary = "Bom分页")
    @GetMapping("product-bom/page")
    public PageInfo<ProductBomDTO> findBomPage(
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam("pageSize") Integer pageSize
    ) {
        ProductDefinitionCriteria criteria = ProductDefinitionCriteria.builder().build();
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return productBomService.findBomPage(criteria);
    }


    @Operation(summary = "创建物料清单")
    @PostMapping("product-bom")
    public void create(@Validated @RequestBody ProductBomCreateRO args) {
        ProductBom productBom = convert.from(args);
        productBomService.createBom(productBom);
    }


    @Operation(summary = "根据产品编码查询物料清单")
    @GetMapping("product-bom/{productCode}/materials")
    public List<ProductMaterialDTO> findProductMaterials(@PathVariable String productCode) {
        return productBomService.findProductMaterials(productCode);
    }

}

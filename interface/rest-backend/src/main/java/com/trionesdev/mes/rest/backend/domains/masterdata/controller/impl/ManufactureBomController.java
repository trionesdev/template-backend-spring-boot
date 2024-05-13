package com.trionesdev.mes.rest.backend.domains.masterdata.controller.impl;

import com.trionesdev.mes.domain.core.domains.masterdata.entity.ManufactureBom;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.ManufactureBomService;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro.ManufactureBomCreateRO;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataBeRestBeanConvert;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "物料清单")
@RequiredArgsConstructor
@RestController
@RequestMapping(MasterDataRestConstants.MASTER_DATA_PATH)
public class ManufactureBomController {
    private final MasterDataBeRestBeanConvert convert;
    private final ManufactureBomService manufactureBomService;

    @Operation(summary = "创建物料清单")
    @PostMapping("manufacture-bom")
    public void create(@Validated @RequestBody ManufactureBomCreateRO args) {
        ManufactureBom manufactureBom = convert.from(args);
        manufactureBomService.create(manufactureBom);
    }

}

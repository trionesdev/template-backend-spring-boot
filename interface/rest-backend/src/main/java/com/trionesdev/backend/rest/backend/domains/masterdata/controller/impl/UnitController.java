package com.trionesdev.backend.rest.backend.domains.masterdata.controller.impl;

import com.trionesdev.backend.rest.backend.domains.masterdata.controller.ro.UnitCreateRO;
import com.trionesdev.backend.rest.backend.domains.masterdata.internal.MasterDataBeRestBeanConvert;
import com.trionesdev.backend.rest.backend.domains.masterdata.internal.MasterDataRestConstants;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.UnitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "单位")
@RequiredArgsConstructor
@RestController
@RequestMapping(MasterDataRestConstants.MASTER_DATA_PATH)
public class UnitController {
    private final MasterDataBeRestBeanConvert masterDataBeRestBeanConvert;
    private final UnitService unitService;

    @Operation(summary = "创建单位")
    @PostMapping("units")
    public void create(@Validated @RequestBody UnitCreateRO args) {
        unitService.create(masterDataBeRestBeanConvert.from(args));
    }

    @Operation(summary = "根据ID删除单位")
    @DeleteMapping("units/{id}")
    public void deleteById(@PathVariable("id") String id) {
        unitService.deleteById(id);
    }

}

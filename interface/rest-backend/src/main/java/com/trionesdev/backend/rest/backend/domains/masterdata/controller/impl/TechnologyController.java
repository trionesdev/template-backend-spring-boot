package com.trionesdev.backend.rest.backend.domains.masterdata.controller.impl;

import com.trionesdev.backend.rest.backend.domains.masterdata.controller.ro.TechnologyCreateRO;
import com.trionesdev.backend.rest.backend.domains.masterdata.internal.MasterDataBeRestBeanConvert;
import com.trionesdev.backend.rest.backend.domains.masterdata.internal.MasterDataRestConstants;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.TechnologyService;
import com.trionesdev.mes.domain.core.dto.masterdata.TechnologyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "工艺")
@RequiredArgsConstructor
@RestController
@RequestMapping(MasterDataRestConstants.MASTER_DATA_PATH)
public class TechnologyController {
    private final MasterDataBeRestBeanConvert masterDataBeRestBeanConvert;
    private final TechnologyService technologyService;

    @Operation(summary = "创建工艺")
    @PostMapping("technologies")
    public void createTechnology(@Validated @RequestBody TechnologyCreateRO args) {
        TechnologyDTO technologyDTO = masterDataBeRestBeanConvert.from(args);
        technologyService.createTechnology(technologyDTO);
    }

}

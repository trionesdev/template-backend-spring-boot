package com.trionesdev.mes.rest.backend.domains.masterdata.controller.impl;

import com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro.ProcessFlowCreateRO;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataBeRestBeanConvert;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataRestConstants;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ProcessFlow;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.ProcessFlowService;
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
public class ProcessFlowController {
    private final MasterDataBeRestBeanConvert masterDataBeRestBeanConvert;
    private final ProcessFlowService technologyService;

    @Operation(summary = "创建工艺")
    @PostMapping("process-flows")
    public void createTechnology(@Validated @RequestBody ProcessFlowCreateRO args) {
        ProcessFlow technology = masterDataBeRestBeanConvert.from(args);
        technologyService.createTechnology(technology);
    }

}

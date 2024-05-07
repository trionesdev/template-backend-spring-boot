package com.trioinesdev.backend.rest.backend.domains.masterdata.controller.impl;

import com.trioinesdev.backend.rest.backend.domains.masterdata.controller.ro.ManufactureProcessCreateRO;
import com.trioinesdev.backend.rest.backend.domains.masterdata.internal.MasterDataBeRestBeanConvert;
import com.trioinesdev.backend.rest.backend.domains.masterdata.internal.MasterDataRestConstants;
import com.trionesdev.mes.domain.core.domains.masterdata.dao.entity.ManufactureProcess;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "生产工艺")
@RequiredArgsConstructor
@RestController
@RequestMapping(MasterDataRestConstants.MASTER_DATA_PATH)
public class ManufactureProcessController {
    private final MasterDataBeRestBeanConvert masterDataBeRestBeanConvert;

    @Operation(summary = "创建生产工序")
    @PostMapping("manufacture-processes")
    public void create(@Validated @RequestBody ManufactureProcessCreateRO args) {
        ManufactureProcess manufactureProcess = masterDataBeRestBeanConvert.from(args);

    }
}

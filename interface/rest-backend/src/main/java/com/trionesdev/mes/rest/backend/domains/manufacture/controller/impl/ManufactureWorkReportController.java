package com.trionesdev.mes.rest.backend.domains.manufacture.controller.impl;

import com.trionesdev.mes.core.domains.manufacture.service.impl.ManufactureWorkReportService;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro.ManufactureWorkReportRO;
import com.trionesdev.mes.rest.backend.domains.manufacture.internal.ManufactureBeBeanConvert;
import com.trionesdev.mes.rest.backend.domains.manufacture.internal.ManufactureConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "报工")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = ManufactureConstants.MANUFACTURE_PATH)
public class ManufactureWorkReportController {
    private final ManufactureBeBeanConvert convert;
    private final ManufactureWorkReportService reportService;

    @Operation(summary = "创建报工")
    @PostMapping(value = "work-reports")
    public void create(@Validated @RequestBody ManufactureWorkReportRO.Create args) {
        var report = convert.from(args);
        reportService.createReport(report);
    }

}

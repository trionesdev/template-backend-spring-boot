package com.trionesdev.mes.rest.backend.domains.manufacture.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.core.domains.manufacture.dto.ManufactureWorkReportDetailDTO;
import com.trionesdev.mes.core.domains.manufacture.service.impl.ManufactureWorkReportService;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.query.ManufactureWorkReportQuery;
import com.trionesdev.mes.rest.backend.domains.manufacture.controller.ro.ManufactureWorkReportRO;
import com.trionesdev.mes.rest.backend.domains.manufacture.internal.ManufactureBeBeanConvert;
import com.trionesdev.mes.rest.backend.domains.manufacture.internal.ManufactureConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "分页查询报工")
    @GetMapping(value = "work-reports/page")
    public PageInfo<ManufactureWorkReportDetailDTO> findPage(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "1") Integer pageSize,
            ManufactureWorkReportQuery query
    ){
        var criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return reportService.findPage(criteria);
    }

}

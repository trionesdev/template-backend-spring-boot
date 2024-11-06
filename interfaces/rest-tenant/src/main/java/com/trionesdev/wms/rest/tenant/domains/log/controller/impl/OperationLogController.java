package com.trionesdev.wms.rest.tenant.domains.log.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.wms.core.domains.log.dto.OperationLogExtDTO;
import com.trionesdev.wms.core.domains.log.service.impl.OperationLogService;
import com.trionesdev.wms.rest.tenant.domains.log.controller.ro.OperationLogQueryRO;
import com.trionesdev.wms.rest.tenant.domains.log.internal.LogTenantRestConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.trionesdev.wms.rest.tenant.domains.log.internal.LogRestConstants.LOG_PATH;

@Tag(name = "日志")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = LOG_PATH)
public class OperationLogController {
    private final LogTenantRestConvert convert;
    private final OperationLogService operationLogService;

    @Operation(summary = "获取日志列表")
    @GetMapping(value = "operation/page")
    public PageInfo<OperationLogExtDTO> findPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            OperationLogQueryRO query) {
        var criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return operationLogService.findPage(criteria);
    }

}

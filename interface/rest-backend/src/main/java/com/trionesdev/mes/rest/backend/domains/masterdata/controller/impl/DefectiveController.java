package com.trionesdev.mes.rest.backend.domains.masterdata.controller.impl;

import com.trionesdev.commons.core.page.PageInfo;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.criteria.DefectiveCriteria;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.DefectivePO;
import com.trionesdev.mes.domain.core.domains.masterdata.service.impl.DefectiveService;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.query.DefectiveQuery;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro.DefectiveCreateRO;
import com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro.DefectiveUpdateRO;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataBeRestBeanConvert;
import com.trionesdev.mes.rest.backend.domains.masterdata.internal.MasterDataRestConstants;
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

import java.util.List;

@Tag(name = "不良品项")
@RequiredArgsConstructor
@RestController
@RequestMapping(MasterDataRestConstants.MASTER_DATA_PATH)
public class DefectiveController {
    private final MasterDataBeRestBeanConvert convert;
    private final DefectiveService defectiveService;

    @Operation(summary = "创建不良品项")
    @PostMapping("defectives")
    public void create(@Validated @RequestBody DefectiveCreateRO args) {
        DefectivePO po = convert.from(args);
        defectiveService.create(po);
    }

    @Operation(summary = "根据ID删除不良品项")
    @DeleteMapping("defectives/{id}")
    public void deleteById(@PathVariable String id) {
        defectiveService.deleteById(id);
    }

    @Operation(summary = "根据ID更新不良品项")
    @PutMapping("defectives/{id}")
    public void updateById(@PathVariable String id, @Validated @RequestBody DefectiveUpdateRO args) {
        DefectivePO po = convert.from(args);
        po.setId(id);
        defectiveService.updateById(po);
    }

    @Operation(summary = "根据ID查询不良品项")
    @GetMapping("defectives/{id}")
    public DefectivePO findById(@PathVariable String id) {
        return defectiveService.findById(id).orElse(null);
    }

    @Operation(summary = "查询不良品项列表")
    @GetMapping("defectives/list")
    public List<DefectivePO> findList(DefectiveQuery query) {
        DefectiveCriteria criteria = convert.from(query);
        return defectiveService.findList(criteria);
    }

    @Operation(summary = "分页查询不良品项")
    @GetMapping("defectives/page")
    public PageInfo<DefectivePO> findPage(
            @RequestParam(value = "pageNum") Integer pageNum,
            @RequestParam(value = "pageSize") Integer pageSize,
            DefectiveQuery query
    ) {
        DefectiveCriteria criteria = convert.from(query);
        criteria.setPageNum(pageNum);
        criteria.setPageSize(pageSize);
        return defectiveService.findPage(criteria);
    }

}

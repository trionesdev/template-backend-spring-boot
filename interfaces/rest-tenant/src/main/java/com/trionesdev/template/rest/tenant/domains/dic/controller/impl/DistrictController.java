package com.trionesdev.template.rest.tenant.domains.dic.controller.impl;


import com.trionesdev.template.core.domains.dic.dao.po.DistrictPO;
import com.trionesdev.template.core.domains.dic.service.impl.DistrictService;
import com.trionesdev.template.rest.tenant.domains.dic.controller.ro.DistrictQueryRO;
import com.trionesdev.template.rest.tenant.domains.dic.internal.DicBeRestConvert;
import com.trionesdev.template.rest.tenant.domains.dic.internal.DicRestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "数据字典/地区数据")
@RequiredArgsConstructor
@RestController
@RequestMapping(DicRestConstants.DIC_PATH)
public class DistrictController {
    private final DicBeRestConvert convert;
    private final DistrictService districtService;

    @Operation(summary = "获取地区数据列表")
    @GetMapping(value = "district/list")
    public List<DistrictPO> findList(DistrictQueryRO query) {
        var criteria = convert.from(query);
        return districtService.findDistricts(criteria);
    }
}

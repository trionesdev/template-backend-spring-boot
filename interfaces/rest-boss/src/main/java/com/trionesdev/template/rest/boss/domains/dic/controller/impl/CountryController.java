package com.trionesdev.template.rest.boss.domains.dic.controller.impl;

import com.trionesdev.template.core.domains.dic.dao.po.CountryPO;
import com.trionesdev.template.core.domains.dic.service.impl.CountryService;
import com.trionesdev.template.rest.boss.domains.dic.controller.ro.CountryQuery;
import com.trionesdev.template.rest.boss.domains.dic.internal.DicBossRestConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.trionesdev.template.rest.boss.domains.dic.internal.DicConstants.DIC_PATH;

@Tag(name = "字典-国家")
@RequiredArgsConstructor
@RestController("boss_countryController")
@RequestMapping(DIC_PATH)
public class CountryController {
    private final DicBossRestConvert convert;
    private final CountryService countryService;

    @Operation(summary = "获取国家列表")
    @GetMapping(value = "country/list")
    public List<CountryPO> findList(CountryQuery query) {
        var criteria = convert.from(query);
        return countryService.findList(criteria);
    }
}

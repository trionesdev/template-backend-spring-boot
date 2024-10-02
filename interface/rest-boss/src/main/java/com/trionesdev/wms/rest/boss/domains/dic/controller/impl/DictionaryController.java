package com.trionesdev.wms.rest.boss.domains.dic.controller.impl;

import com.trionesdev.wms.core.domains.dic.dao.po.DictionaryPO;
import com.trionesdev.wms.core.domains.dic.service.impl.DictionaryService;
import com.trionesdev.wms.rest.boss.domains.dic.controller.ro.DictionaryCreateRO;
import com.trionesdev.wms.rest.boss.domains.dic.controller.ro.DictionaryUpdateRO;
import com.trionesdev.wms.rest.boss.domains.dic.internal.DicBossRestConvert;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.trionesdev.wms.rest.boss.domains.dic.internal.DicConstants.DIC_PATH;

@Tag(name = "字典-字典")
@RequiredArgsConstructor
@RestController("boss_dictionaryController")
@RequestMapping(DIC_PATH)
public class DictionaryController {
    private final DicBossRestConvert convert;
    private final DictionaryService dictionaryService;

    @Operation(summary = "创建字典")
    @PostMapping(value = "dictionaries")
    public void create(@Validated @RequestBody DictionaryCreateRO args) {
        var dic = convert.from(args);
        dictionaryService.createDictionary(dic);
    }

    @Operation(summary = "根据ID修改字典")
    @PutMapping(value = "dictionaries/{id}")
    public void updateById(@PathVariable(value = "id") String id, @Validated @RequestBody DictionaryUpdateRO args) {
        var dic = convert.from(args);
        dic.setId(id);
        dictionaryService.updateDictionaryById(dic);
    }

    @Operation(summary = "根据ID删除字典")
    @DeleteMapping(value = "dictionaries/{id}")
    public void deleteById(@PathVariable(value = "id") String id) {
        dictionaryService.deleteDictionaryById(id);
    }

    @Operation(summary = "根据ID获取字典")
    @GetMapping(value = "dictionaries/{id}")
    public DictionaryPO findDictionaryById(@PathVariable(value = "id") String id) {
        return dictionaryService.findDictionaryById(id).orElse(null);
    }

}

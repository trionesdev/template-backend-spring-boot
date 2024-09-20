package com.trionesdev.wms.rest.boss.domains.dic.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.trionesdev.wms.rest.boss.domains.dic.controller.internal.DicConstants.DIC_PATH;

@Tag(name = "字典-字典")
@RequiredArgsConstructor
@RestController("boss_dictionaryController")
@RequestMapping(DIC_PATH)
public class DictionaryController {
}

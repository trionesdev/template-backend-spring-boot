package com.trionesdev.template.core.domains.base.internal;


import com.trionesdev.template.core.domains.base.internal.aggregate.entity.CodeFormatRule;
import com.trionesdev.template.core.domains.base.shared.enums.TimeFormatType;

import java.util.List;

public class BaseConstants {
    public static final List<CodeFormatRule> DEFAULT_CODE_FORMAT_RULES = List.of(
            CodeFormatRule.builder().name("基础数据/产品定义").identifier("PRODUCT_DEFINITION").prefix("PD").timeFormatType(TimeFormatType.YYYY_MM_DD).serialNumberDigits(4).build(),
            CodeFormatRule.builder().name("基础数据/不良品项").identifier("DEFECTIVE").prefix("BLP").timeFormatType(TimeFormatType.YYYY_MM_DD).serialNumberDigits(4).build(),
            CodeFormatRule.builder().name("基础数据/工序").identifier("MANUFACTURE_PROCESS").prefix("GX").timeFormatType(TimeFormatType.YYYY_MM_DD).serialNumberDigits(4).build(),
            CodeFormatRule.builder().name("基础数据/工艺路线").identifier("PROCESS_FLOW").prefix("GYLX").timeFormatType(TimeFormatType.YYYY_MM_DD).serialNumberDigits(4).build(),
            CodeFormatRule.builder().name("生产管理/工单").identifier("MANUFACTURE_ORDER").prefix("GD").timeFormatType(TimeFormatType.YYYY_MM_DD).serialNumberDigits(4).build(),
            CodeFormatRule.builder().name("仓库管理/仓库").identifier("WAREHOUSE").prefix("CK").timeFormatType(TimeFormatType.YYYY_MM_DD).serialNumberDigits(4).build(),
            CodeFormatRule.builder().name("客户管理/客户").identifier("CUSTOMER").prefix("KH").timeFormatType(TimeFormatType.YYYY_MM_DD).serialNumberDigits(4).build(),
            CodeFormatRule.builder().name("供应商管理/供应商").identifier("SUPPLIER").prefix("GYS").timeFormatType(TimeFormatType.YYYY_MM_DD).serialNumberDigits(4).build()
    );
}

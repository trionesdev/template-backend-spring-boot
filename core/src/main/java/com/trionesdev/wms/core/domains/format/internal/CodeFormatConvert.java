package com.trionesdev.wms.core.domains.format.internal;

import com.trionesdev.wms.core.domains.format.dao.po.CodeFormatRulePO;
import com.trionesdev.wms.core.domains.format.internal.aggregate.entity.CodeFormatRule;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface CodeFormatConvert {
    CodeFormatRule codeFormatRulePoToEntity(CodeFormatRulePO po);
    CodeFormatRulePO codeFormatEntityToPo(CodeFormatRule entity);
}

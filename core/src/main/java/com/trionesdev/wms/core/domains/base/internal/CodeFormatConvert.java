package com.trionesdev.wms.core.domains.base.internal;

import com.trionesdev.wms.core.domains.base.dao.po.CodeFormatRulePO;
import com.trionesdev.wms.core.domains.base.dto.CodeFormatRuleDTO;
import com.trionesdev.wms.core.domains.base.internal.aggregate.entity.CodeFormatRule;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, builder = @Builder(disableBuilder = true))
public interface CodeFormatConvert {
    CodeFormatRule codeFormatRulePoToEntity(CodeFormatRulePO po);

    CodeFormatRulePO codeFormatEntityToPo(CodeFormatRule entity);

    CodeFormatRuleDTO codeFormatEntityToDto(CodeFormatRule entity);
}

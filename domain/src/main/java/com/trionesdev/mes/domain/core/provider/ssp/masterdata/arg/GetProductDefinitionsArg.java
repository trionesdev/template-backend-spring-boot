package com.trionesdev.mes.domain.core.provider.ssp.masterdata.arg;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class GetProductDefinitionsArg {
    private String name;
    private String code;
}

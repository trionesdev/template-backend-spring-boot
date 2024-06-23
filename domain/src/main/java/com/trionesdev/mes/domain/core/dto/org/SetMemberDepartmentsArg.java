package com.trionesdev.mes.domain.core.dto.org;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class SetMemberDepartmentsArg {
    private String memberId;
    private List<String> departmentIds;
}

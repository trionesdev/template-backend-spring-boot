package com.trionesdev.wms.core.domains.org.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
public class SetMemberDepartmentsCmd {
    private String memberId;
    private List<String> departmentIds;
}

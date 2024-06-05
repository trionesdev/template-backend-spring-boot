package com.trionesdev.mes.rest.backend.domains.masterdata.controller.query;

import lombok.Data;

import java.util.List;

@Data
public class ProcessesByFlowIdsQuery {
    private List<String> flowIds;
}

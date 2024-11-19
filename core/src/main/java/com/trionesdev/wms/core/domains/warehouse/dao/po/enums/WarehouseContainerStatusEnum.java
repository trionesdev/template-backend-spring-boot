package com.trionesdev.wms.core.domains.warehouse.dao.po.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum WarehouseContainerStatusEnum {
    NORMAL("正常"),
    SCRAP("报废");

    @Getter
    private final String desc;
}

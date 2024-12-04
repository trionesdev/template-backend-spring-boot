package com.trionesdev.wms.rest.tenant.domains.goods.controller.ro;

import lombok.Data;

@Data
public class GoodsQueryRO {
    private String code;
    private String name;
    private Boolean enabled;
}

package com.trionesdev.mes.rest.backend.domains.masterdata.controller.ro;

import com.trionesdev.mes.domain.core.domains.masterdata.entity.ManufactureBom;
import com.trionesdev.mes.domain.core.domains.masterdata.entity.ManufactureBom.Material;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ManufactureBomItemPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ManufactureBomCreateRO {
    private String productCode;
    private List<Material> materials;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Material {
        private String productCode;
        private BigDecimal unitUsage;
        private String processCode;
        private String remark;
    }
}

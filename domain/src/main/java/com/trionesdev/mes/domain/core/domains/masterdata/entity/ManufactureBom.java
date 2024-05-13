package com.trionesdev.mes.domain.core.domains.masterdata.entity;

import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ManufactureBomItemPO;
import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ManufactureBomPO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ManufactureBom {
    private String id;
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

        public ManufactureBomItemPO toPo() {
            return ManufactureBomItemPO.builder()
                    .productCode(productCode)
                    .unitUsage(unitUsage)
                    .processCode(processCode)
                    .remark(remark)
                    .build();
        }

    }


    public ManufactureBomPO toPo() {
        return ManufactureBomPO.builder()
                .id(id)
                .productCode(productCode)
                .build();
    }



}

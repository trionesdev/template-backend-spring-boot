package com.trionesdev.mes.domain.core.domains.masterdata.entity;

import com.trionesdev.mes.domain.core.domains.masterdata.repository.po.ProductMaterialPO;
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
public class ProductBom {
    private String id;
    private String productCode;
    private List<Material> materials;

    @Data
    @SuperBuilder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Material {
        private String materialCode;
        private BigDecimal unitUsage;
        private String processCode;
        private String remark;

        public ProductMaterialPO toPo() {
            return ProductMaterialPO.builder()
                    .materialCode(materialCode)
                    .unitUsage(unitUsage)
                    .processCode(processCode)
                    .remark(remark)
                    .build();
        }

    }

}

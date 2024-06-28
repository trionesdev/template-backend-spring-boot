package com.trionesdev.mes.core.domains.masterdata.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 生产BOM详情
 */
@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "master_data_product_material")
public class ProductMaterialPO extends BasePO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String productCode;
    private String materialCode;
    private BigDecimal unitUsage;
    private String processCode;
    private String remark;
}

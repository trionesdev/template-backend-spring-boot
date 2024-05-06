package com.trionesdev.mes.domain.core.domains.masterdata.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.entity.BaseLogicEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "md_product_definition")
public class ProductDefinition extends BaseLogicEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String name;
    private String code;
    private String unit;
    private String specification;
    private String type;
    private String technologicalId; //工艺路线
    private String defaultSupplierId;
    private Integer maxInventory;
    private Integer minInventory;
    private BigDecimal unitCost; //成本单价
    private BigDecimal unitPrice; //销售单价
}

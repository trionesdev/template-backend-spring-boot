package com.trionesdev.wms.core.domains.warehouse.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BaseLogicPO;
import com.trionesdev.wms.core.domains.warehouse.dao.po.enums.WarehouseContainerStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "warehouse_warehouse_container")
public class WarehouseContainerPO extends BaseLogicPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String warehouseId;
    private String code;
    private String name;
    private String type;
    private WarehouseContainerStatusEnum status;
    @TableField(value = "is_enabled")
    private Boolean enabled;
    private String remark;
}

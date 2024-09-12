package com.trionesdev.wms.core.domains.custom.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "custom_code_serial_number")
public class CustomCodeSerialNumberPO  {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String identifier;
    private String timeIdentifier;
    private Integer serialNumber;
}

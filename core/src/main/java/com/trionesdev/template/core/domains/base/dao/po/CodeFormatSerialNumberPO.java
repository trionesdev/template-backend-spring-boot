package com.trionesdev.template.core.domains.base.dao.po;

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
@TableName(value = CodeFormatSerialNumberPO.TABLE_NAME)
public class CodeFormatSerialNumberPO {
    static final String TABLE_NAME = "triones_base_code_serial_number";

    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String identifier;
    private String timeIdentifier;
    private Integer serialNumber;
}

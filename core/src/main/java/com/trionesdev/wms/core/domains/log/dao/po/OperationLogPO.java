package com.trionesdev.wms.core.domains.log.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@TableName(value = "log_operation_log", autoResultMap = true)
public class OperationLogPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String operatorId;
    private String operatorRole;
    private String objId;
    private String type;
    private String action;
    private String description;
    private Instant startAt;
    private Instant endAt;
    @TableField(value = "is_success")
    private Boolean success;
}

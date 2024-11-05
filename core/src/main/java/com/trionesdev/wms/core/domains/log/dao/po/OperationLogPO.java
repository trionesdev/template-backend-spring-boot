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
import java.util.Map;

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
    private String actorId;
    private String actorRole;
    private String objId;
    private String type;
    private String category;
    private String action;
    private String description;
    private Instant startAt;
    private Instant endAt;
    @TableField(value = "is_success")
    private Boolean success;
    private String errorMsg;
    private String beforeValues;
    private String afterValues;
    private Map<String,String> extra;
}

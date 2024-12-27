package com.trionesdev.template.core.domains.log.dao.po;

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
@TableName(value = "log_login_log", autoResultMap = true)
public class LoginLogPO {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String tenantId;
    private String actorId;
    private String actorRole;
    private String username;
    private String nickname;
    private String ip;
    private String district;
    private String type;
    private String category;
    private String action;
    private String description;
    private Instant startAt;
    @TableField(value = "is_success")
    private Boolean success;
    private String errorMsg;
}

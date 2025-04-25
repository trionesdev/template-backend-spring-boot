package com.trionesdev.template.core.domains.bossuser.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.trionesdev.commons.mybatisplus.po.BaseLogicPO;
import com.trionesdev.template.core.domains.bossuser.shared.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = BossUserPO.TABLE_NAME)
public class BossUserPO extends BaseLogicPO {
    static final String TABLE_NAME = "triones_boss_user";
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String username;
    private String encodedPassword;
    private String phone;
    private String email;
    private String avatar;
    private Gender gender;
    private String nickname;
    private Instant birthday;
    @TableField(value = "is_enabled")
    private Boolean enabled;
}

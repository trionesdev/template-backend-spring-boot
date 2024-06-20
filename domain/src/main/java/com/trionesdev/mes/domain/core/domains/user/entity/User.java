package com.trionesdev.mes.domain.core.domains.user.entity;

import cn.hutool.core.util.StrUtil;
import com.trionesdev.mes.domain.core.domains.user.internal.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String id;
    private String username;
    private String password;
    private String encodedPassword;
    private String phone;
    private String email;
    private String avatar;
    private GenderEnum gender;
    private String firstName;
    private String lastName;
    private String nickname;
    private Instant birthday;
    private Boolean enabled;

    public String getEncodedPassword() {
        if (StrUtil.isNotBlank(password)) {
            return new BCryptPasswordEncoder().encode(password);
        }
        return null;
    }
}

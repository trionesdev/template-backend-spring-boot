package com.trionesdev.template.core.domains.user.internal.entity;

import cn.hutool.core.util.StrUtil;
import com.trionesdev.template.core.domains.user.internal.enums.GenderEnum;
import com.trionesdev.template.infrastructure.ddd.AggregateRoot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.regex.Pattern;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User implements AggregateRoot<String> {
    private String id;
    private String account;
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
        if (StrUtil.isNotBlank(encodedPassword)) {
            return encodedPassword;
        }
        if (StrUtil.isNotBlank(password)) {
            return new BCryptPasswordEncoder().encode(password);
        }
        return null;
    }

    public AccountType getAccountType() {
        if (Pattern.matches("0?(13|14|15|17|18|19)[0-9]{9}", account)) {
            return AccountType.PHONE;
        } else {
            return AccountType.USERNAME;
        }
    }

    public enum AccountType {
        USERNAME,
        PHONE,
        EMAIL
    }

    public Boolean passwordMatch(String encryptedPassword) {
        return new BCryptPasswordEncoder().matches(password, encryptedPassword);
    }

}

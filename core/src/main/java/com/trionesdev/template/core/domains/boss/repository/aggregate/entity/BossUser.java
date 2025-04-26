package com.trionesdev.template.core.domains.boss.repository.aggregate.entity;

import com.trionesdev.template.core.domains.boss.shared.enums.AccountType;
import com.trionesdev.template.core.domains.boss.shared.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.Instant;
import java.util.regex.Pattern;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BossUser {
    private String id;
    private String account;
    private String username;
    private String password;
    private String encodedPassword;
    private String phone;
    private String email;
    private String avatar;
    private Gender gender;
    private String nickname;
    private Instant birthday;
    private Boolean enabled;

    public String getEncodedPassword() {
        if (StringUtils.isNotBlank(encodedPassword)) {
            return encodedPassword;
        }
        if (StringUtils.isNotBlank(password)) {
            return new BCryptPasswordEncoder().encode(password);
        }
        return null;
    }


    public Boolean passwordMatch(String encryptedPassword) {
        return new BCryptPasswordEncoder().matches(password, encryptedPassword);
    }

    public AccountType getAccountType() {
        if (Pattern.matches("0?(13|14|15|17|18|19)[0-9]{9}", account)) {
            return AccountType.PHONE;
        } else {
            return AccountType.USERNAME;
        }
    }

}

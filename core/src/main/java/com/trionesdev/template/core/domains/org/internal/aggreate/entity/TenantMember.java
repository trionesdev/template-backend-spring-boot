package com.trionesdev.template.core.domains.org.internal.aggreate.entity;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.regex.Pattern;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TenantMember {

    private String id;
    private String tenantId;
    private String userId;
    private Boolean master;
    private String username;
    private String phone;
    private String email;
    private String password;
    private String encodedPassword;
    private String nickname;
    private String avatar;
    private String name;
    private List<String> departmentIds;

    public String getEncodedPassword() {
        if (StrUtil.isNotBlank(encodedPassword)) {
            return encodedPassword;
        }
        if (StrUtil.isNotBlank(password)) {
            return new BCryptPasswordEncoder().encode(password);
        }
        return null;
    }

    public enum AccountType {
        USERNAME,
        PHONE,
        EMAIL
    }

    public static AccountType getAccountType(String account) {
        if (Pattern.matches("0?(13|14|15|17|18|19)[0-9]{9}", account)) {
            return AccountType.PHONE;
        } else {
            return AccountType.USERNAME;
        }
    }

    public Boolean passwordMatch(String password) {
        return new BCryptPasswordEncoder().matches(password, encodedPassword);
    }

}

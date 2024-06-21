package com.trionesdev.mes.domain.core.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Pattern;

@Data
@SuperBuilder
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountSignInArg {
    private String account;
    private String password;

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

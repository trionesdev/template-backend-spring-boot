package com.trionesdev.mes.domain.core.dto.tenant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class TenantMemberSignInArg {
    private String tenantSerial;
    private String username;
    private String password;

    public Boolean passwordMatch(String encryptedPassword) {
        return new BCryptPasswordEncoder().matches(password, encryptedPassword);
    }

}

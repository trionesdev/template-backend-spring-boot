package com.trionesdev.template.core.domains.user.dto;

import com.trionesdev.template.core.domains.user.shared.enums.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String id;
    private String username;
    private String phone;
    private String email;
    private String avatar;
    private GenderEnum gender;
    private String firstName;
    private String lastName;
    private String nickname;
    private Instant birthday;
}

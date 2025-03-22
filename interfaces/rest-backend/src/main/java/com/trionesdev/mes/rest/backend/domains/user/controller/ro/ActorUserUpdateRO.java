package com.trionesdev.template.rest.backend.domains.user.controller.ro;

import lombok.Data;

import java.time.Instant;

@Data
public class ActorUserUpdateRO {
    private String nickname;
    private String avatar;
    private Instant birthday;
}

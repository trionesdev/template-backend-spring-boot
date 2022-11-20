package ms.triones.backend.rest.backend.modules.user.controller.ro;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRO {
    @NotNull
    private Long userId;
}

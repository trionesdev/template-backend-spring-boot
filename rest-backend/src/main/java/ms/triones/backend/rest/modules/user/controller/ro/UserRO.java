package ms.triones.backend.rest.modules.user.controller.ro;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRO {
    @NotNull
    private Long userId;
}

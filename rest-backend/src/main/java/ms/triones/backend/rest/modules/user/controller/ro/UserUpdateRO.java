package ms.triones.backend.rest.modules.user.controller.ro;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserUpdateRO {
    @NotBlank
    private String id;
    @NotBlank
    private String name;
}

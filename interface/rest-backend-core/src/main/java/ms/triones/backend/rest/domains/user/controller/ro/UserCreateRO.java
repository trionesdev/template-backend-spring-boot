package ms.triones.backend.rest.domains.user.controller.ro;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateRO {
    @NotBlank
    private String name;
}
